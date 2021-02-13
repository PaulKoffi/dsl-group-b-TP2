package fr.unice.polytech.groupB.CinEditorML.kernel.behavioral;

import fr.unice.polytech.groupB.CinEditorML.kernel.assemblor.Visitable;

public interface FrontElement extends Element, Visitable {
    int duration= 0;

    public String getName();
}
