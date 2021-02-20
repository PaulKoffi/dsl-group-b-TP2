package fr.unice.polytech.groupB.CinEditorML.kernel.assemblor;

import java.util.HashMap;
import java.util.Map;
import fr.unice.polytech.groupB.CinEditorML.kernel.App;
import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.*;
import fr.unice.polytech.groupB.CinEditorML.kernel.structural.*;

public abstract class Visitor<T> {

	public abstract void visit(App app);
	public abstract void visit(Video video);
	public abstract void visit(TextClip textClip);
	public abstract void visit(Subtitle subtitle);
	public abstract void visit(Audio audio);
	public abstract void visit(SpecificVideoPart specificVideoPart);
	public abstract void visit(TextClipWithAnimation textClipWithAnimation);
	public abstract void visit(EndingTextClip endingTextClip);

	/***********************
	 ** Helper mechanisms **
	 ***********************/

	protected T result;

	public T getResult() {
		return result;
	}
}

