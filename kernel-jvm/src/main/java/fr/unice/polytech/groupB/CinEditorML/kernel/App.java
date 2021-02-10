package fr.unice.polytech.groupB.CinEditorML.kernel;

import java.util.ArrayList;

import fr.unice.polytech.groupB.CinEditorML.kernel.assemblor.*;
import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.*;

public class App implements NamedElement, Visitable {

	private String name;
	private ArrayList<BackGroundElement> backGroundElements = new ArrayList<>();
	private ArrayList<FrontElement> frontElements = new ArrayList<>();

	public ArrayList<BackGroundElement> getBackGroundElements() {
		return backGroundElements;
	}

	public ArrayList<FrontElement> getFrontElements() {
		return frontElements;
	}


	public void addBackGroundElement(BackGroundElement backGroundElement){
		backGroundElements.add(backGroundElement);
	}

	public void addFrontElement(FrontElement frontElement){
		frontElements.add(frontElement);
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
