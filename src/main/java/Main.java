import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {
    public static void main(String[] args) throws ParseException, FileNotFoundException {
        //create on desktop 2 file - ___.xlsx for exel and ___.txt for code work
        //date like YYYY.MM.DD
        String start = "2024.09.22";
        String end = "2024.09.23";
        /*
        ----------------------------------------------------------------------------------------------------------------
         */
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        Date dateStart = format.parse(start);
        Date dateEnd = format.parse(end);
        long startL = dateStart.getTime();
        long endL = dateEnd.getTime();
        parseBybit parse = new parseBybit();
        betaToExel exel = new betaToExel();
        parse.parse(startL, endL);
        exel.toExel();
    }
}
