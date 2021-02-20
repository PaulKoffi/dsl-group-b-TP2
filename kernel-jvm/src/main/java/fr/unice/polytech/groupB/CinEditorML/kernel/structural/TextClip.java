package fr.unice.polytech.groupB.CinEditorML.kernel.structural;
import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.*;
import fr.unice.polytech.groupB.CinEditorML.kernel.assemblor.*;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.Animation;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.BackGroundElementType;

public class TextClip extends BackGroundElement {
    private String text;
    private int time;

    public TextClip(){

    }

    @Override
    public BackGroundElementType getBackGroundElementType() {
        return BackGroundElementType.TEXT_CLIP;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public boolean isBackground(){
        return true;
    }
}
