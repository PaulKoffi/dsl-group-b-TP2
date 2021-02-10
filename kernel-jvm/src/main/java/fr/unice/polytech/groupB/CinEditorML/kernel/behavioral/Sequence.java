package fr.unice.polytech.groupB.CinEditorML.kernel.behavioral;
import fr.unice.polytech.groupB.CinEditorML.kernel.assemblor.*;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.BackGroundElementType;

public abstract class Sequence implements Visitable {

    public abstract BackGroundElementType getType();
    public abstract String getName();

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
