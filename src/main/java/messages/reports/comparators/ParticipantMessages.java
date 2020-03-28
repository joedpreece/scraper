package messages.reports.comparators;

import messages.components.Participant;

import java.util.Comparator;

public class ParticipantMessages implements Comparator<Participant> {

    @Override
    public int compare(Participant participant, Participant t1) {
        int value0 = participant.getMessages().size();
        int value1 = t1.getMessages().size();

        if (value0 > value1) {
            return 1;
        } else if (value1 > value0) {
            return -1;
        } else {
            return 0;
        }
    }
}
