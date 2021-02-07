package fr.unice.polytech.groupB.CinEditorML.kernel.structural;

import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.*;
import fr.unice.polytech.groupB.CinEditorML.kernel.generator.*;

public class Video extends Sequence {
    private String name;
    private String path;

    @Override
    public SequenceType getType() {
        return SequenceType.VIDEO;
    }

    @Override
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

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
