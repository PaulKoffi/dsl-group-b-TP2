package fr.unice.polytech.groupB.CinEditorML.kernel.behavioral;
import fr.unice.polytech.groupB.CinEditorML.kernel.generator.*;

public abstract class Sequence implements Visitable {

    public abstract SequenceType getType();
    public abstract String getName();

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
