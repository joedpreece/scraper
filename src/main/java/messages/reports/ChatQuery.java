package messages.reports;

import messages.components.Chat;
import messages.components.Message;
import messages.components.Participant;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ChatQuery {

    public static String titleOfChat(Chat chat) {
        return chat.getTitle();
    }

    public static Map<Participant, Integer> listChatParticipants(Chat chat) {
        Map<Participant, Integer> orderedParticipants = new HashMap<>();
        for (Map.Entry<Integer, Participant> participantEntry : chat.getParticipants().entrySet()) {
            Participant participant = participantEntry.getValue();
            Set<Message> messagesSent = new HashSet<>(participant.getMessages().values());
            int chatMessages = 0;
            for (Message message : messagesSent) {
                if (message.getChat() == chat) {
                    chatMessages++;
                }
            }
            orderedParticipants.put(participant, chatMessages);
        }
        orderedParticipants = FacebookMessengerQuery.sortByValue(orderedParticipants);
        return orderedParticipants;
    }

    public static int totalChatMessages(Chat chat) {
        return chat.getMessages().size();
    }

}
