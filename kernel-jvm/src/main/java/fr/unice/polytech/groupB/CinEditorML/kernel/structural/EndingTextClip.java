package fr.unice.polytech.groupB.CinEditorML.kernel.structural;
import fr.unice.polytech.groupB.CinEditorML.kernel.assemblor.Visitor;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.Animation;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.BackGroundElementType;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.FrontElementType;

public class EndingTextClip extends TextClip {

    private float speed = 1;

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Animation getAnimation() {
        return Animation.SCROLL;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public BackGroundElementType getBackGroundElementType() {
        return BackGroundElementType.TEXT_ENDING;
    }
}
