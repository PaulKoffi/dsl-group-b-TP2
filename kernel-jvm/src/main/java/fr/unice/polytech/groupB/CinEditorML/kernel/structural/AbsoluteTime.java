package fr.unice.polytech.groupB.CinEditorML.kernel.structural;

import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.Element;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.TimeType;

public class AbsoluteTime implements Time{
    Element element;
    @Override
    public TimeType getType() {
        return TimeType.ABSOLUTE;
    }
}
