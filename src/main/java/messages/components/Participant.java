package messages.components;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Participant {

    private final String name;
    private Map<Integer, Chat> chats;
    private Map<Integer, Message> messages;

    public Participant(String name) {
        this.name = name;
        this.chats = new HashMap<>();
        this.messages = new HashMap<>();
    }

    public Map<Integer, Chat> getChats() {
        return chats;
    }

    public void setChats(Map<Integer, Chat> chats) {
        this.chats = chats;
    }

    public Map<Integer, Message> getMessages() {
        return messages;
    }

    public void setMessages(Map<Integer, Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void addChat(Chat chat) {
        this.chats.put(chat.hashCode(), chat);
    }

    public void addMessage(Message message) {
        this.messages.put(message.hashCode(), message);
    }

}
