import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: magzhan.karasayev
 * Date: 28.12.13
 * Time: 12:09
 */
public class Main {
    public static void main(String[] args) throws Exception {
        URL csvFile = Thread.currentThread().getContextClassLoader().getResource("test.xlsx");
        Poi.printSqlPoi(new File(csvFile.toURI()));
    }
}
