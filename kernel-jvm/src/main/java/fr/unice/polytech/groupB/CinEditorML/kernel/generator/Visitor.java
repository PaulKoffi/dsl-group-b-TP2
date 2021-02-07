package fr.unice.polytech.groupB.CinEditorML.kernel.generator;

import java.util.HashMap;
import java.util.Map;

import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.Action;
import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.State;
import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.TemporalTransition;
import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.Transition;
import fr.unice.polytech.groupB.CinEditorML.kernel.structural.Actuator;
import fr.unice.polytech.groupB.CinEditorML.kernel.structural.Sensor;
import fr.unice.polytech.groupB.CinEditorML.kernel.App;
import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.*;
import fr.unice.polytech.groupB.CinEditorML.kernel.structural.*;

public abstract class Visitor<T> {

	public abstract void visit(App app);

	public abstract void visit(State state);
	public abstract void visit(Transition transition);
	public abstract void visit(TemporalTransition temporalTransition);
	public abstract void visit(Action action);

	public abstract void visit(Actuator actuator);
	public abstract void visit(Sensor sensor);
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

