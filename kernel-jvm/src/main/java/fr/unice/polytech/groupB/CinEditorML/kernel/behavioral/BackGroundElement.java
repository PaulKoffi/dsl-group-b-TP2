package fr.unice.polytech.groupB.CinEditorML.kernel.behavioral;

import fr.unice.polytech.groupB.CinEditorML.kernel.NamedElement;
import fr.unice.polytech.groupB.CinEditorML.kernel.assemblor.Visitable;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.BackGroundElementType;

public abstract class BackGroundElement extends Element implements  Visitable {
    public abstract String getName();
    public abstract BackGroundElementType getBackGroundElementType();

}
