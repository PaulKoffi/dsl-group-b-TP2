package fr.unice.polytech.groupB.CinEditorML.kernel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import fr.unice.polytech.groupB.CinEditorML.kernel.assemblor.*;
import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.*;

public class App implements NamedElement, Visitable {

	private String name;
	private LinkedHashMap<String,BackGroundElement> backGroundElements = new LinkedHashMap<>();
	private LinkedHashMap<String,FrontElement> frontElements = new LinkedHashMap<>();
	private ArrayList<String> sequence = new ArrayList<>();

	public LinkedHashMap<String,BackGroundElement> getBackGroundElements() {
		return backGroundElements;
	}

	public LinkedHashMap<String,FrontElement> getFrontElements() {
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
