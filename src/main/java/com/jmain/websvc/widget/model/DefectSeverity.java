package com.jmain.websvc.widget.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DefectSeverity {
	MINOR("MINOR"), MAJOR("MAJOR"), MASSIVE("MASSIVE");
	
	private DefectSeverity(String value) {
		this.value = value;
	}
	
	private final String value;	
	
	@JsonValue
	public String value() {
		return value;
	}
}
