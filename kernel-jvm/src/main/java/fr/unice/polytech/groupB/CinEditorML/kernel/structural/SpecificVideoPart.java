package fr.unice.polytech.groupB.CinEditorML.kernel.structural;

import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.Sequence;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.BackGroundElementType;

public class SpecificVideoPart extends Sequence {

    public SpecificVideoPart(){

    }
    @Override
    public BackGroundElementType getType() {
        return BackGroundElementType.VIDEO;
    }

    @Override
    public String getName() {
        return "";
    }
}
