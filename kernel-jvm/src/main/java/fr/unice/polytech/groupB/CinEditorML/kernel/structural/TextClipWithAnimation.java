package fr.unice.polytech.groupB.CinEditorML.kernel.structural;

import fr.unice.polytech.groupB.CinEditorML.kernel.utils.Animation;

public class TextClipWithAnimation extends TextClip {

    private float speed;
    private int  animationDuration;
    private Animation animation;

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getAnimationDuration() {
        return animationDuration;
    }

    public void setAnimationDuration(int animationDuration) {
        this.animationDuration = animationDuration;
    }


    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }
}
