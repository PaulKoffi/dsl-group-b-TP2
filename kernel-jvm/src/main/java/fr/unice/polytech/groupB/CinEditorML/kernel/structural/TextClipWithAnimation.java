package fr.unice.polytech.groupB.CinEditorML.kernel.structural;

import fr.unice.polytech.groupB.CinEditorML.kernel.assemblor.Visitor;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.Animation;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.BackGroundElementType;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.FrontElementType;

public class TextClipWithAnimation extends TextClip {

    private Animation animation;

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public BackGroundElementType getBackGroundElementType() {
        return BackGroundElementType.TEXT_CLIP_ANIMATION;
    }
}
