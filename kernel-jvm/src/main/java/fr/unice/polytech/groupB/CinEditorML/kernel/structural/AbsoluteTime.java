package fr.unice.polytech.groupB.CinEditorML.kernel.structural;

import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.Element;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.TimeType;

public class AbsoluteTime extends Time{
    int time;

    @Override
    public TimeType getType() {
        return TimeType.ABSOLUTE;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
