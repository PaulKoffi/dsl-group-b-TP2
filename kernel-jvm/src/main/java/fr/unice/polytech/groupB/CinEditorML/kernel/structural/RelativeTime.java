package fr.unice.polytech.groupB.CinEditorML.kernel.structural;

import fr.unice.polytech.groupB.CinEditorML.kernel.utils.TimeType;

public class RelativeTime implements Time{
    @Override
    public TimeType getType() {
        return TimeType.RELATIVE;
    }
}
