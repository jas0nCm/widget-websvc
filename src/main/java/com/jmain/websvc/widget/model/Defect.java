package com.jmain.websvc.widget.model;

import com.fasterxml.jackson.annotation.JsonValue;

public interface Defect {	
	
	public DefectSeverity getDefectSeverity();
	
	public String getDefectType();
	
	public boolean isValidDefectSeverity(DefectSeverity defectSeverity);
}
