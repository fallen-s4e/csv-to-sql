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
        URL csvFile = Thread.currentThread().getContextClassLoader().getResource("test.csv");
        printSql(new File(csvFile.toURI()));
    }

    private static void printSql(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        try {
            String line = br.readLine();
            while (line != null) {
                printSqlLine(line);
                line = br.readLine();
            }
        } finally {
            br.close();
        }
    }

    private static void printSqlLine(String line) {
        String sqlLine = toSqlLine(line);
        if (sqlLine != null) {
            System.out.println(sqlLine);
        }
    }

    private static String toSqlLine(String line) {
        try {
            String[] strings = line.split(";");
            String dtk = strings[0];
            String bin = strings[1];
            String trn = strings[2];
            String traderName = strings[3].substring(1, strings[3].length()-2).replace("\"\"", "\"");
            String kbk = strings[4];
            String sum = strings[5].replace(" ", "");
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
