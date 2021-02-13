package fr.unice.polytech.groupB.CinEditorML.kernel;

import java.util.ArrayList;
import java.util.HashMap;

import fr.unice.polytech.groupB.CinEditorML.kernel.assemblor.*;
import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.*;

public class App implements NamedElement, Visitable {

	private String name;
	private HashMap<String,BackGroundElement> backGroundElements = new HashMap<>();
	private HashMap<String,FrontElement> frontElements = new HashMap<>();
	private ArrayList<String> sequence = new ArrayList<>();

	public HashMap<String,BackGroundElement> getBackGroundElements() {
		return backGroundElements;
	}

	public HashMap<String,FrontElement> getFrontElements() {
		return frontElements;
	}


	public void addBackGroundElement(String key,BackGroundElement backGroundElement){
		backGroundElements.put(key,backGroundElement);
	}

	public void addFrontElement(String key,FrontElement frontElement){
		frontElements.put(key,frontElement);
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

	public ArrayList<String> getSequence() {
		return sequence;
	}

	public void setSequence(ArrayList<String> sequence) {
		this.sequence = sequence;
	}
}
