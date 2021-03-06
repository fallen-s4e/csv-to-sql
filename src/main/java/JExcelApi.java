import jxl.*;
import jxl.read.biff.BiffException;

import java.io.File;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: magzhan.karasayev
 * Date: 29.12.13
 * Time: 18:43
 */
public class JExcelApi {

    public static String printSql(File file) throws Exception {
        try {
            Workbook w = Workbook.getWorkbook(file);
            // Get the first sheet
            Sheet sheet = w.getSheet(0);
            // Loop over first 10 column and lines

            for (int j = 0; j < sheet.getColumns(); j++) {
                for (int i = 0; i < sheet.getRows(); i++) {
                    Cell cell = sheet.getCell(j, i);
                    CellType type = cell.getType();
                    if (type == CellType.LABEL) {
                        System.out.println("I got a label "
                                + cell.getContents());
                    }

                    if (type == CellType.NUMBER) {
                        System.out.println("I got a number "
                                + cell.getContents());
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void run() throws Exception {
        URL csvFile = Thread.currentThread().getContextClassLoader().getResource("test.xls");
        printSql(new File(csvFile.toURI()));
    }
}
