package fr.unice.polytech.groupB.CinEditorML.kernel.structural;
import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.*;
import fr.unice.polytech.groupB.CinEditorML.kernel.assemblor.*;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.Animation;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.BackGroundElementType;

public class TextClip implements BackGroundElement {
    private String name;
    private String text;
    private int time;
    private Animation animation;

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public TextClip(){

    }

    @Override
    public BackGroundElementType getBackGroundElementType() {
        return BackGroundElementType.TEXT_CLIP;
    }

    @Override
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
