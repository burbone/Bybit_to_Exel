import com.bybit.api.client.domain.CategoryType;
import com.bybit.api.client.domain.market.MarketInterval;
import com.bybit.api.client.domain.market.request.MarketDataRequest;
import com.bybit.api.client.service.BybitApiClientFactory;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class parseBybit {
    public void parse (long start, long end) throws FileNotFoundException {
        //change files path
        PrintStream console = new PrintStream(""); //for txt

        var client = BybitApiClientFactory.newInstance().newAsyncMarketDataRestClient();
        long startPlus = 0;
        System.out.println("parse start");
        while (start < end) {
            startPlus = start + 3_600_000 - 1;
/*                                  /\
            change this by interval ||
            1 - 60_000
            3 - 180_000
            5 - 300_000
            15 - 900_000
            30 - 1_800_000
            60 - 1h - 3_600_000
            240 - 4h - 14_400_000
            360m - 6h - 21_600_000
            720m - 12h - 43_200_000
            1_440 - 1d - 86_400_000
            10_080 - 7d - 604_800_000
*/

            var marketKLineRequest = MarketDataRequest
                    .builder()
                    .start(start)
                    .category(CategoryType.LINEAR)
                    .symbol("XRPUSDT")
// change this for other symbol |
                    .marketInterval(MarketInterval.ONE_MINUTE)
// change this                                        |
                    .end(startPlus)
                    .build();
            client.getMarketPriceLinesData(marketKLineRequest, console::println);
            start = startPlus + 1;
            try {
                Thread.sleep(100);
//change this for faster complite
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("parse end");
    }
}
