package fr.unice.polytech.groupB.CinEditorML.kernel.assemblor;

import java.util.HashMap;
import java.util.Map;
import fr.unice.polytech.groupB.CinEditorML.kernel.App;
import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.*;
import fr.unice.polytech.groupB.CinEditorML.kernel.structural.*;

public abstract class Visitor<T> {

	public abstract void visit(App app);
	public abstract void visit(Sequence sequence);
	public abstract void visit(Video video);
	public abstract void visit(TextClip textClip);


//	public abstract void visit(State state);
//	public abstract void visit(Transition transition);
//	public abstract void visit(TemporalTransition temporalTransition);
//	public abstract void visit(Actuator actuator);
//	public abstract void visit(Sensor sensor);
//	public abstract void visit(ConditionAction conditionAction);

	/***********************
	 ** Helper mechanisms **
	 ***********************/

	protected Map<String,Object> context = new HashMap<>();

	protected T result;

	public T getResult() {
		return result;
	}

}

