package messages.components;

import java.util.Objects;

public class Message {

    private final Chat chat;
    private final Participant sender;
    private final long timestamp;
    private String content;

    public Message(Chat chat, Participant sender, long timestamp) {
        this.chat = chat;
        this.sender = sender;
        this.sender.addMessage(this);
        this.timestamp = timestamp;
    }

    public Chat getChat() {
        return chat;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sender=" + sender +
                ", timestamp=" + timestamp +
                ", content='" + content + '\'' +
                '}';
    }

    public Participant getSender() {
        return sender;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



}
