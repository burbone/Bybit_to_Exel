import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {
    public static void main(String[] args) throws ParseException, FileNotFoundException {
        infoTaker info = new infoTaker();
        info.takeAll();
        String start = info.getStartTime();
        String end = info.getEndTime();
        String symbol = info.getSymbol();
        String interval = info.getInterval();

        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        Date dateStart = format.parse(start);
        Date dateEnd = format.parse(end);
        long startL = dateStart.getTime();
        long endL = dateEnd.getTime();

        parseBybit parse = new parseBybit();
        parse.parse(startL, endL, symbol, interval);
        betaToExel exel = new betaToExel();
        exel.toExel();
    }
}