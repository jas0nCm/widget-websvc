package com.jmain.websvc.widget.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Widget {
	private static final int MINIMUM_SPROCKET_COUNT = 1;
	
	private Casing frontCasing;
	private Casing rearCasing;
	private Hinge hinge;
	private List<Sprocket> sprockets;
	private WidgetGeneration generation;
	
	@JsonCreator
	public Widget(@JsonProperty("frontCasing") Casing frontCasing, @JsonProperty("rearCasing") Casing rearCasing,
			@JsonProperty("hinge") Hinge hinge, @JsonProperty("sprockets") List<Sprocket> sprockets) throws Exception {
		this.frontCasing = frontCasing;
		this.rearCasing = rearCasing;
		this.hinge = hinge;
		try {
			generation = calculateGeneration(sprockets);
		}
		catch (Exception e) {
			throw new Exception("Error constructing Widget: " + e.getMessage());
		}
		this.sprockets = sprockets;
	}
	
	public Casing getFrontCasing() {
		return frontCasing;
	}
	
	public Casing getRearCasing() {
		return rearCasing;
	}
	
	public Hinge getHinge() {
		return hinge;
	}
	
	public List<Sprocket> getSprockets() {
		return sprockets;
	}
	
	public Sprocket getSprocket(int sprocketNum) throws Exception{
		if (sprocketNum >= MINIMUM_SPROCKET_COUNT && sprocketNum <= generation.sprocketCount()) {
			return sprockets.get(sprocketNum);
		}
		else {
			throw new Exception("The Widget does not have a Sprocket at that position");
		}
	}
	
	@JsonIgnore
	public WidgetGeneration getGeneration() {
		return generation;
	}
	
	WidgetGeneration calculateGeneration(List<Sprocket> sprockets) throws Exception {
		int numSprockets = sprockets.size();
		if (WidgetGeneration.FIRST.sprocketCount() == numSprockets) {
			return WidgetGeneration.FIRST;
		}
		else if (WidgetGeneration.SECOND.sprocketCount() == numSprockets) {
			return WidgetGeneration.SECOND;
		}
		else if (WidgetGeneration.THIRD.sprocketCount() == numSprockets) {
			return WidgetGeneration.THIRD;
		}
		else if (WidgetGeneration.FOURTH.sprocketCount() == numSprockets) {
			return WidgetGeneration.FOURTH;
		}
		else if (WidgetGeneration.FIFTH.sprocketCount() == numSprockets) {
			return WidgetGeneration.FIFTH;
		}
		else {
			throw new Exception("No Sprokets or irregular number of Sprockets found");
		}
	}
}
 