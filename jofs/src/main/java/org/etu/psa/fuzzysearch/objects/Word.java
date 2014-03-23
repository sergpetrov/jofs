package org.etu.psa.fuzzysearch.objects;

public class Word {
	private String value;
	private int priority;
	
	public Word(String value, int priority) {
		this.value = value;
		this.priority = priority;
	}
	
	public String getValue() {
		return value;
	}

	public int getPriority() {
		return priority;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
