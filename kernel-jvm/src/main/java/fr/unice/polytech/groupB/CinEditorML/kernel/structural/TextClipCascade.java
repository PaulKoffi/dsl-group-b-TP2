package fr.unice.polytech.groupB.CinEditorML.kernel.structural;

public class TextClipCascade {

    private float speed;
    private int  animationDuration;

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

    public String getAnimation() {
        return "Cascade";
    }
}
