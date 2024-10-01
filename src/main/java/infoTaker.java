import java.util.Scanner;
public class infoTaker {
    String startTime;
    String endTime;
    String interval;
    String symbol;
    Scanner scan = new Scanner(System.in);
    public void takeAll() {
        setStartTime();
        setEndTime();
        setSymbol();
        setInterval();
    }
    public void setStartTime() {
        System.out.println("Print start time like YYYY.MM.DD");
        this.startTime = scan.nextLine();
    }
    public void setEndTime() {
        System.out.println("Print end time like YYYY.MM.DD");
        this.endTime = scan.nextLine();
    }
    public void setSymbol() {
        System.out.println("Print your symbol like XRPUSDT");
        this.symbol = scan.nextLine();
    }
    public void setInterval() {
        System.out.println("Print your interval like 1m, 3m, 5m, 15m, 30m, 60m, 4h, 6h, 12h, 1d, 7d");
        this.interval = scan.nextLine();
    }
    public String getStartTime() {
        return startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public String getInterval() {
        return interval;
    }
    public String getSymbol() {
        return symbol;
    }
}
