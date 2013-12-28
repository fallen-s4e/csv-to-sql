import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created with IntelliJ IDEA.
 * User: magzhan.karasayev
 * Date: 28.12.13
 * Time: 18:14
 */
public class Poi {
    public static String printSqlPoi(File file) throws Exception {
        Workbook wb = WorkbookFactory.create(new FileInputStream(file));
        Sheet sheet = wb.getSheetAt(0);

        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            String sql = rowToSql(row);
            if (sql != null) {
                System.out.println(sql);
            }
        }
        return null;
    }

    private static String rowToSql(Row row) {
        try {
            String dtk = String.valueOf(row.getCell(0).getNumericCellValue());
            String bin = row.getCell(1).getStringCellValue();
            String trn = row.getCell(2).getStringCellValue();
            String traderName = row.getCell(3).getStringCellValue();
            String kbk = row.getCell(4).getStringCellValue();
            String sum = row.getCell(5).getStringCellValue();

            if (dtk.matches("\\d{5}") &&
                    bin.matches("\\d{12}")) {
                return String.format(
                        "INSERT INTO CONTROL_FINE\n" +
                                "(ID, DTK, trader_bin, trader_trn, trader_name, kbk, sum, username, last_edit_date,DELETED) \n" +
                                "VALUES((select max(id)+1 from control_fine), " +
                                "'%s', '%s', '%s', '%s', '%s', '%s', 'system', sysdate, '0');",
                        dtk, bin, trn, traderName, kbk, sum);
            }
        }catch (Exception e) {
        }
        return null;
    }
}
