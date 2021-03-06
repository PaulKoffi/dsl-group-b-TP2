package fr.unice.polytech.groupB.CinEditorML.kernel.structural;
import fr.unice.polytech.groupB.CinEditorML.kernel.assemblor.Visitor;
import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.FrontElement;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.Color;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.FrontElementType;

public class Subtitle extends FrontElement{

    private Time time;
    private String text;
    private int duration;

    public Subtitle() {
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Time getTime() {
        return time;
    }

    public String getText() {
        return this.text;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public FrontElementType getFrontElementType() {
        return FrontElementType.SUBTITLE;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean isBackground(){
        return false;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
