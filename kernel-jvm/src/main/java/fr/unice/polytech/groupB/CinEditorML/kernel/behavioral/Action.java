package fr.unice.polytech.groupB.CinEditorML.kernel.behavioral;

import fr.unice.polytech.groupB.CinEditorML.kernel.structural.Actuator;
import fr.unice.polytech.groupB.CinEditorML.kernel.structural.SIGNAL;
import fr.unice.polytech.groupB.CinEditorML.kernel.generator.Visitable;
import fr.unice.polytech.groupB.CinEditorML.kernel.generator.Visitor;

public class Action implements Visitable {

	private SIGNAL value;
	private Actuator actuator;


	public SIGNAL getValue() {
		return value;
	}

	public void setValue(SIGNAL value) {
		this.value = value;
	}

	public Actuator getActuator() {
		return actuator;
	}

	public void setActuator(Actuator actuator) {
		this.actuator = actuator;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
