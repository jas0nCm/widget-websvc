package com.jmain.websvc.widget.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class HingeDefect implements Defect {
	private enum HingeDefectType {
		MISALIGNED_HINGE("MISALIGNED_HINGE"), BENT_HINGE("BENT_HINGE"), BROKEN_HINGE("BROKEN_HINGE");
		
		private HingeDefectType(String value) {
			this.value = value;
		}
		
		private String value;
		
		@JsonValue
		public String value() {
			return value;
		}
	}
	
	private final static Map<DefectSeverity, HingeDefectType> defectMap;
	
	static {
		defectMap = new HashMap<>();
		defectMap.put(DefectSeverity.MINOR, HingeDefectType.MISALIGNED_HINGE);
		defectMap.put(DefectSeverity.MAJOR, HingeDefectType.BENT_HINGE);
		defectMap.put(DefectSeverity.MASSIVE, HingeDefectType.BROKEN_HINGE);
	}
	
	private DefectSeverity defectSeverity;
	private HingeDefectType defectType;
	
	@JsonCreator
	public HingeDefect(@JsonProperty("defectSeverity") DefectSeverity defectSeverity) throws Exception {
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
			throw new Exception("DefectSeverity " + defectSeverity + " is not valid for HingeDefect");
		}
	}
	
	public DefectSeverity getDefectSeverity() {
		return this.defectSeverity;
	}
	
	public String getDefectType() {
		return this.defectType.value();
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
