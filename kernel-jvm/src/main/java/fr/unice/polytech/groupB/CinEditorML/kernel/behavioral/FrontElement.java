package fr.unice.polytech.groupB.CinEditorML.kernel.behavioral;

import fr.unice.polytech.groupB.CinEditorML.kernel.assemblor.Visitable;
import fr.unice.polytech.groupB.CinEditorML.kernel.structural.Time;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.FrontElementType;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.TimeType;

public abstract class FrontElement  extends Element implements  Visitable {
    public abstract FrontElementType getFrontElementType();
    public abstract Time getTime();
    public abstract int getDuration();
}
