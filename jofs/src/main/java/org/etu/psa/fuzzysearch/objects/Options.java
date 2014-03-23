package org.etu.psa.fuzzysearch.objects;

public class Options {

	private int distance;
	
	public Options() {
		
	}
	
	public Options(int maxDistance) {
		this.setDistance(maxDistance);
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
}
