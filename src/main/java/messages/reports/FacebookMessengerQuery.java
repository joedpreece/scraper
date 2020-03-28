package messages.reports;

import messages.components.Chat;
import messages.components.FacebookMessenger;
import messages.components.Participant;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FacebookMessengerQuery {

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Collections.reverse(list);
        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static void listAllParticipants(FacebookMessenger facebookMessenger) {
        Map<Participant, Integer> messageCount = new HashMap<>();
        for (Map.Entry<Integer, Participant> entry : facebookMessenger.getParticipants().entrySet()) {
            messageCount.put(entry.getValue(), entry.getValue().getMessages().size());
        }
        messageCount = sortByValue(messageCount);
        AtomicInteger sum = new AtomicInteger();
        messageCount.forEach((k,v) -> {
            System.out.println(sum + "\t" + k + "\t" + v);
            sum.getAndIncrement();
        });
    }

    public static void listSoloChatParticipants(FacebookMessenger facebookMessenger) {

    }

}
