package fr.unice.polytech.groupB.CinEditorML.kernel.structural;
import fr.unice.polytech.groupB.CinEditorML.kernel.assemblor.Visitor;
import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.FrontElement;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.FrontElementType;
public class Audio extends FrontElement {
    private Time time;
    private String name;
    private String path;
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
    @Override
    public void setName(String name) {
        this.name= name;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
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