package fr.unice.polytech.groupB.CinEditorML.kernel.structural;

import fr.unice.polytech.groupB.CinEditorML.kernel.assemblor.Visitor;
import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.BackGroundElement;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.BackGroundElementType;

public class SpecificVideoPart extends Video {

    private String beginning;
    private String ending;

    public SpecificVideoPart(){

    }

    @Override
    public BackGroundElementType getBackGroundElementType() {
        return BackGroundElementType.SPECIFIC_VIDEO;
    }

    public String getBeginning() {
        return beginning;
    }

    public void setBeginning(String beginning) {
        this.beginning = beginning;
    }

    public String getEnding() {
        return ending;
    }

    public void setEnding(String ending) {
        this.ending = ending;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean isBackground(){
        return true;
    }
}
