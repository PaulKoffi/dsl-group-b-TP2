package fr.unice.polytech.groupB.CinEditorML.kernel;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.Sequence;
import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.State;
import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.Transition;
import fr.unice.polytech.groupB.CinEditorML.kernel.generator.Visitable;
import fr.unice.polytech.groupB.CinEditorML.kernel.generator.Visitor;
import fr.unice.polytech.groupB.CinEditorML.kernel.structural.Brick;

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
