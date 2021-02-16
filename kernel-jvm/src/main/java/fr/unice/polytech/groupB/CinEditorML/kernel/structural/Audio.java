package fr.unice.polytech.groupB.CinEditorML.kernel.structural;
import fr.unice.polytech.groupB.CinEditorML.kernel.assemblor.Visitor;
import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.FrontElement;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.FrontElementType;

public class Audio implements FrontElement {

    private Time time;
    private String name;
    private String videoName;
    private float volume;

    public Audio() {
    }

    @Override
    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    @Override
    public FrontElementType getFrontElementType() {
        return FrontElementType.AUDIO;
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
    public int getDuration(){
        return 0;
    }
}
