package fr.unice.polytech.groupB.CinEditorML.kernel.behavioral;

public class TextClip implements Sequence{
    private String name;
    private String text;


    @Override
    public SequenceType getType() {
        return SequenceType.TEXT_CLIP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
