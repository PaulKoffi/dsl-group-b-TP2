package fr.unice.polytech.groupB.CinEditorML.kernel.behavioral;

import fr.unice.polytech.groupB.CinEditorML.kernel.assemblor.Visitable;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.BackGroundElementType;

public interface BackGroundElement extends Element, Visitable {
    String name = "";

    public String getName();
    public BackGroundElementType getBackGroundElementType();
    public boolean isBackground();
}
