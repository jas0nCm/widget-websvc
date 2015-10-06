package com.jmain.websvc.widget.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class CasingDefect implements Defect {
	private enum CasingDefectType {
		CRACKED_CASING("CRACKED_CASING");
		
		private CasingDefectType(String value) {
			this.value = value;
		}
		
		private String value;
		
		@JsonValue
		public String value() {
			return value;
		}
	}
	
	private final static Map<DefectSeverity, CasingDefectType> defectMap;
	
	static {
		defectMap = new HashMap<>();
		defectMap.put(DefectSeverity.MAJOR, CasingDefectType.CRACKED_CASING);
	}
	
	private DefectSeverity defectSeverity;
	private CasingDefectType defectType;

	@JsonCreator
	public CasingDefect(@JsonProperty("defectSeverity") DefectSeverity defectSeverity) throws Exception {
		if (DefectSeverity.MAJOR == defectSeverity) {
			this.defectSeverity = defectSeverity;
			this.defectType = defectMap.get(DefectSeverity.MAJOR);
		}
		else {
			throw new Exception("DefectSeverity " + defectSeverity + " is not valid for CasingDefect");
		}
	}
	
	@Override
	public DefectSeverity getDefectSeverity() {
		return defectSeverity;
	}

	@Override
	public String getDefectType() {
		return defectType.value();
	}

	@Override
	public boolean isValidDefectSeverity(DefectSeverity defectSeverity) {
		if (defectMap.containsKey(defectSeverity)) {
			return true;
		}
		else {
			return false;
		}
	}
}
