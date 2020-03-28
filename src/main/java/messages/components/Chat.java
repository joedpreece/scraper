package messages.components;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Chat {

    private final Map<Integer, Participant> participants;
    private final Map<Integer, Message> messages;
    private final String title;

    @Override
    public String toString() {
        return "Chat{" +
                "title='" + title + '\'' +
                "uniqueID='" + uniqueID() + '\'' +
                '}';
    }

    public Chat(Map<Integer, Participant> participants, Map<Integer, Message> messages, String title) {
        this.participants = participants;
        this.messages = messages;
        this.title = title;
    }

    public Map<Integer, Participant> getParticipants() {
        return participants;
    }

    public Map<Integer, Message> getMessages() {
        return messages;
    }

    public String getTitle() {
        return title;
    }

    public void addParticipant(Participant participant) {
        this.participants.put(participant.hashCode(), participant);
        participant.addChat(this);
    }

    public int uniqueID() {
        return Objects.hash(participants, messages, title);
    }

}
