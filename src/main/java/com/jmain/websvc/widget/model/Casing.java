package com.jmain.websvc.widget.model;

public class Casing implements WidgetComponent {
	CasingDefect majorDefect;
	
	public Casing() {}
	
	public Casing(CasingDefect majorDefect) {
		this.majorDefect = majorDefect;
	}
	
	public CasingDefect getMajorDefect() {
		return majorDefect;
	}
	
	public void setMajorDefect(CasingDefect majorDefect) {
		this.majorDefect = majorDefect;
	}

	@Override
	public Defect getDefect(DefectSeverity defectSeverity) {
		
		if (DefectSeverity.MAJOR == defectSeverity) {
			return getMajorDefect();
		}
		else {
			return null;
		}
	}
}
