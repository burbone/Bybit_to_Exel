import com.bybit.api.client.domain.CategoryType;
import com.bybit.api.client.domain.market.MarketInterval;
import com.bybit.api.client.domain.market.request.MarketDataRequest;
import com.bybit.api.client.service.BybitApiClientFactory;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class parseBybit {
    public void parse (long start, long end, String symbol, String interval) throws FileNotFoundException {
        try (PrintStream console = new PrintStream("intermediate.txt")) {
            var client = BybitApiClientFactory.newInstance().newAsyncMarketDataRestClient();
            long startPlus;
            System.out.println("parse start");
            long timeIn = 0;
            MarketInterval it = null;
            switch (interval) {
                case "1m":
                    it = MarketInterval.ONE_MINUTE;
                    timeIn = 60_000;
                    break;
                case "3m":
                    it = MarketInterval.THREE_MINUTES;
                    timeIn = 180_000;
                    break;
                case "5m":
                    it = MarketInterval.FIVE_MINUTES;
                    timeIn = 300_000;
                    break;
                case "15m":
                    it = MarketInterval.FIFTEEN_MINUTES;
                    timeIn = 900_000;
                    break;
                case "30m":
                    it = MarketInterval.HALF_HOURLY;
                    timeIn = 1_800_000;
                    break;
                case "60m":
                    it = MarketInterval.HOURLY;
                    timeIn = 3_600_000;
                    break;
                case "4h":
                    it = MarketInterval.FOUR_HOURLY;
                    timeIn = 14_400_000;
                    break;
                case "6h":
                    it = MarketInterval.SIX_HOURLY;
                    timeIn = 21_600_000;
                    break;
                case "12h":
                    it = MarketInterval.TWELVE_HOURLY;
                    timeIn = 43_200_000;
                    break;
                case "1d":
                    it = MarketInterval.DAILY;
                    timeIn = 86_400_000;
                    break;
                case "7d":
                    it = MarketInterval.WEEKLY;
                    timeIn = 604_800_000;
                    break;
                default:
                    System.out.println("error interval type");
            }
            while (start < end) {
                startPlus = start + timeIn - 1;
                var marketKLineRequest = MarketDataRequest
                        .builder()
                        .start(start)
                        .category(CategoryType.LINEAR)
                        .symbol(symbol)
                        .marketInterval(it)
                        .end(startPlus)
                        .build();
                client.getMarketPriceLinesData(marketKLineRequest, console::println);
                start = startPlus + 1;


                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println("parse end");
    }
}
