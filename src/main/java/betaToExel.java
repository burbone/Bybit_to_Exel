import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.Date;
import java.util.Scanner;

public class betaToExel {
    public void toExel() throws FileNotFoundException {
        //change files path
        File exelFile = new File(""); //for exel
        File txtFile = new File("");  //for txt
        /*
        ----------------------------------------------------------------------------------------------------------------
         */
        Scanner scanTxt = new Scanner(txtFile);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        System.out.println("exel copy start");
        int i = 0;
        while (scanTxt.hasNext()) {
            String str = scanTxt.nextLine();
            int start = str.indexOf("[[") + 2;
            int end = str.indexOf("]]");
            str = str.substring(start, end);
            String[] strAll = str.split(",");
            String timeMl = strAll[0];
            String open = strAll[1];
            String close = strAll[4];
            String low = strAll[3];
            String high = strAll[2]; 
            Date date = new Date(Long.parseLong(timeMl));
            Row row = sheet.createRow(i);
            row.createCell(0).setCellValue(timeMl);
            row.createCell(1).setCellValue(date.toString());
            row.createCell(2).setCellValue(open);
            row.createCell(3).setCellValue(close);
            row.createCell(4).setCellValue(low);
            row.createCell(5).setCellValue(high);
            i++;
        }
        try {
            FileOutputStream fileOut = new FileOutputStream(exelFile);
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            System.out.println("error");
        }

        System.out.println("exel copy end");
    }
}
