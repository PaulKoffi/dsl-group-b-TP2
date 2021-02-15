package fr.unice.polytech.groupB.CinEditorML.kernel.behavioral;

import fr.unice.polytech.groupB.CinEditorML.kernel.assemblor.Visitable;
import fr.unice.polytech.groupB.CinEditorML.kernel.structural.Time;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.FrontElementType;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.TimeType;

public interface FrontElement extends Element, Visitable {
    public String getName();
    public FrontElementType getFrontElementType();
    public Time getTime();
    public boolean isBackground();
    public int getDuration();
}
