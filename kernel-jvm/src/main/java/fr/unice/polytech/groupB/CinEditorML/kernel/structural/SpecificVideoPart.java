package fr.unice.polytech.groupB.CinEditorML.kernel.structural;

import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.Sequence;
import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.SequenceType;

public class SpecificVideoPart extends Sequence {
    @Override
    public SequenceType getType() {
        return SequenceType.SPECIFIC_VIDEO;
    }

    @Override
    public String getName() {
        return "";
    }
}
