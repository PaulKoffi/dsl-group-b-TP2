package fr.unice.polytech.groupB.CinEditorML.kernel.behavioral;

public abstract class Sequence implements Visitable {

    abstract SequenceType getType();

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
