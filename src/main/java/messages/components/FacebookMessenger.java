package messages.components;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FacebookMessenger {

    private Map<Integer, Participant> participants;
    private Map<Integer, Chat> chats;
    private Map<Integer, Message> messages;

    public FacebookMessenger(ArrayList<File> files) throws IOException {
        this.participants = new HashMap<>();
        this.chats = new HashMap<>();
        this.messages = new HashMap<>();
        addAllChats(files);
    }

    public Map<Integer, Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(Map<Integer, Participant> participants) {
        this.participants = participants;
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

    public void addSingleChat(String filePath) throws IOException {
        File file = new File(filePath);
        Chat chat = importChatFromJSONFile(file);
        this.chats.put(chat.hashCode(), chat);
    }

    public void addAllChats(ArrayList<File> files) throws IOException {
        for (File file : files) {
            Chat chat = importChatFromJSONFile(file);
            this.chats.put(chat.uniqueID(), chat);
        }
    }

    private Chat importChatFromJSONFile(File file) throws IOException {

        JsonParser jsonParser = new JsonParser();
        JsonReader jsonReader = new JsonReader(new FileReader(file));
        JsonElement jsonElement = jsonParser.parse(jsonReader);

        JsonElement jsonParticipants = jsonElement.getAsJsonObject().get("participants");
        JsonElement jsonMessages = jsonElement.getAsJsonObject().get("messages");
        JsonElement jsonTitle = jsonElement.getAsJsonObject().get("title");

        Map<Integer, Participant> chatParticipants = new HashMap<>();
        Map<Integer, Message> chatMessages = new HashMap<>();
        String title = jsonTitle.getAsString();
        Chat chat = new Chat(chatParticipants, chatMessages, title);

        for (JsonElement element : jsonParticipants.getAsJsonArray()) {
            String name = element.getAsJsonObject().get("name").getAsString();
            Participant participant = new Participant(name);
            if (this.participants.containsKey(participant.hashCode())) {
                participant = participants.get(participant.hashCode());
            } else {
                participants.put(participant.hashCode(), participant);
            }
            chatParticipants.put(participant.hashCode(), participant);
            participant.addChat(chat);
        }

        for (JsonElement element : jsonMessages.getAsJsonArray()) {
            JsonObject jsonMessage = element.getAsJsonObject();
            //System.out.println(message);
            String sender = jsonMessage.get("sender_name").getAsString();
            long timestamp = jsonMessage.get("timestamp_ms").getAsLong();


            Participant participant = new Participant(sender);
            if (this.participants.containsKey(participant.hashCode())) {
                participant = participants.get(participant.hashCode());
            } else {
                participants.put(participant.hashCode(), participant);
            }

            Message message = new Message(chat, participant, timestamp);
            messages.put(message.hashCode(), message);
            chatMessages.put(message.hashCode(), message);

            if (jsonMessage.has("content")) {
                String content = jsonMessage.get("content").getAsString();
                message.setContent(content);
            }

        }

        return chat;
    }
}
