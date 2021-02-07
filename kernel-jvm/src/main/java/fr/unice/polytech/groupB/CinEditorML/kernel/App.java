package fr.unice.polytech.groupB.CinEditorML.kernel;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.groupB.CinEditorML.kernel.generator.*;
import fr.unice.polytech.groupB.CinEditorML.kernel.structural.*;
import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.*;

public class App implements NamedElement, Visitable {

	private String name;
	private ArrayList<Sequence> sequences;

	public ArrayList<Sequence> getSequences() {
		return sequences;
	}

	public void addSequence(Sequence sequence){
		sequences.add(sequence);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
