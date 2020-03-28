package messages.reports;

import messages.components.FacebookMessenger;
import messages.components.Participant;

public class ParticipantQuery {

    public static void participantInformation(FacebookMessenger facebookMessenger, String participantName) {
        Participant participant = new Participant(participantName);
        participant = facebookMessenger.getParticipants().get(participant.hashCode());
        participant.getChats().forEach((k, v) -> {
            System.out.println(v);
        });
    }

}
