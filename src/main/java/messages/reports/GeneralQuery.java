package messages.reports;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class GeneralQuery {

    public static <K, V> void outputMapWithRanks(Map<K, V> map) {
        AtomicInteger sum = new AtomicInteger();
        sum.getAndIncrement();
        map.forEach((k,v) -> {
            System.out.println(sum + "\t" + String.format("%-20s", k) + "\t" + v);
            sum.getAndIncrement();
        });
    }

}
