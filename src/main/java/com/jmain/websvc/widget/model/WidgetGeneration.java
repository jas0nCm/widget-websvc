package com.jmain.websvc.widget.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum WidgetGeneration {
	FIRST("FIRST", 6, 4, 2, 1), SECOND("SECOND", 5, 2, 1, 0), THIRD("THIRD", 3, 2, 0, 0), FOURTH("FOURTH", 2, 1, 0, 0), FIFTH("FIFTH", 1, 0, 0, 0);
	
	WidgetGeneration(String value, int sprocketCount,
			int minorIgnored, int majorIgnored, int massiveIgnored) {
		this.value = value;
		this.sprocketCount = sprocketCount;
		this.minorIgnored = minorIgnored;
		this.majorIgnored = majorIgnored;
		this.massiveIgnored = massiveIgnored;
	}

	private final String value;
	private final int sprocketCount;
	private final int minorIgnored;
	private final int majorIgnored;
	private final int massiveIgnored;
	
	@JsonValue
	public String value() {
		return this.value;
	}
	
	public int sprocketCount() {
		return sprocketCount;
	}
	
	public int minorIgnored() {
		return minorIgnored;
	}
	
	public int majorIgnored() {
		return majorIgnored;
	}
	
	public int massiveIgnored() {
		return massiveIgnored;
	}
}
