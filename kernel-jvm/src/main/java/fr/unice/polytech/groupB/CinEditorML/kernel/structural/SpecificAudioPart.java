package fr.unice.polytech.groupB.CinEditorML.kernel.structural;

import fr.unice.polytech.groupB.CinEditorML.kernel.assemblor.Visitor;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.FrontElementType;

public class SpecificAudioPart extends Audio {

    private String beginning;
    private String ending;

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
    public FrontElementType getFrontElementType() {
        return FrontElementType.SPECIFIC_AUDIO;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean isBackground(){
        return false;
    }
}
