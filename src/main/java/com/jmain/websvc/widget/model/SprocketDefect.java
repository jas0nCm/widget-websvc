package com.jmain.websvc.widget.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class SprocketDefect implements Defect {
	private enum SprocketDefectType {
		FADED_SPROCKET("FADED_SPROCKET"), CRACKED_SPROCKET("CRACKED_SPROCKET"), WARPED_SPROCKET("WARPED_SPROCKET");
		
		private SprocketDefectType(String value) {
			this.value = value;
		}
		
		private String value;
		
		@JsonValue
		public String value() {
			return value;
		}
	}
	
	private final static Map<DefectSeverity, SprocketDefectType> defectMap;
	
	static {
		defectMap = new HashMap<>();
		defectMap.put(DefectSeverity.MINOR, SprocketDefectType.FADED_SPROCKET);
		defectMap.put(DefectSeverity.MAJOR, SprocketDefectType.CRACKED_SPROCKET);
		defectMap.put(DefectSeverity.MASSIVE, SprocketDefectType.WARPED_SPROCKET);
	}
	
	private DefectSeverity defectSeverity;
	private SprocketDefectType defectType;
	
	@JsonCreator
	public SprocketDefect(@JsonProperty("defectSeverity") DefectSeverity defectSeverity) throws Exception {
		if (DefectSeverity.MINOR == defectSeverity) {
			this.defectSeverity = defectSeverity;
			this.defectType = defectMap.get(DefectSeverity.MINOR);
		}
		else if (DefectSeverity.MAJOR == defectSeverity) {
			this.defectSeverity = defectSeverity;
			this.defectType = defectMap.get(DefectSeverity.MAJOR);
		}
		else if (DefectSeverity.MASSIVE == defectSeverity) {
			this.defectSeverity = defectSeverity;
			this.defectType = defectMap.get(DefectSeverity.MASSIVE);
		}
		else {
			throw new Exception("DefectSeverity " + defectSeverity + " is not for SprocketDefect");
		}
	}
	
	public DefectSeverity getDefectSeverity() {
		return this.defectSeverity;
	}
	
	public String getDefectType() {
		return this.defectType.value;
	}
	
	public boolean isValidDefectSeverity(DefectSeverity defectSeverity) {
		if (defectMap.containsKey(defectSeverity)) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
