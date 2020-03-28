package messages.process;

import messages.components.Chat;
import messages.components.FacebookMessenger;
import messages.reports.ChatQuery;
import messages.reports.GeneralQuery;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static String file0 = "/home/joe/Documents/scraper/facebook-joedpreece/messages/inbox/joepreece_9gjo0lstug/message_1.json";
    static String file1 = "/home/joe/Documents/scraper/facebook-joedpreece/messages/inbox/fuckitimgoingi_zbgfbn3hbw/message_1.json";
    static String file2 = "/home/joe/Documents/scraper/facebook-joedpreece/messages/inbox/katewillett_xtp3q-c99a/message_1.json";

    public static void main(String[] args) throws IOException {

        ArrayList<File> files = Importer.obtainFilePaths("/home/joe/Documents/scraper/facebook-joedpreece/messages/inbox/");

        FacebookMessenger facebookMessenger = new FacebookMessenger(files);
        System.out.println(facebookMessenger.getChats());

        int ostrich = 1547346796;
        int oldBowmance = -1746769055;
        int fuckItImGoingI = 1085568585;

        Chat chat = facebookMessenger.getChats().get(1547346796);
        ChatQuery.titleOfChat(chat);
        GeneralQuery.outputMapWithRanks(ChatQuery.listChatParticipants(chat));
        System.out.println(ChatQuery.totalChatMessages(chat));
        System.out.println();

        //ParticipantQuery.participantInformation(facebookMessenger, "India Wilson");

    }

}
