package fr.unice.polytech.groupB.CinEditorML.kernel.behavioral;

public class Video implements Sequence{
    private String name;
    private String path;

    @Override
    public SequenceType getType() {
        return SequenceType.VIDEO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
