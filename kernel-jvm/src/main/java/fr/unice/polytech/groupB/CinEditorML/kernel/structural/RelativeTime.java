package fr.unice.polytech.groupB.CinEditorML.kernel.structural;

import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.Element;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.TimeType;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.Position;

public class RelativeTime extends Time{
    Element element;
    public Position position;
    private int timeComparedToPosition;

    @Override
    public TimeType getType() {
        return TimeType.RELATIVE;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public int getTimeComparedToPosition() {
        return timeComparedToPosition;
    }

    public void setTimeComparedToPosition(int timeComparedToPosition) {
        this.timeComparedToPosition = timeComparedToPosition;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
