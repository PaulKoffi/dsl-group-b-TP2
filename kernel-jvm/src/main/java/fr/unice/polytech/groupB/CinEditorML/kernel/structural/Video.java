package fr.unice.polytech.groupB.CinEditorML.kernel.structural;

import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.*;
import fr.unice.polytech.groupB.CinEditorML.kernel.assemblor.*;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.BackGroundElementType;

public class Video implements BackGroundElement {
    private String name ;
    private String path ;

    public Video(){
    }

    @Override
    public BackGroundElementType getBackGroundElementType() {
        return BackGroundElementType.VIDEO;
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
