package com.jmain.websvc.widget.model;

public class Hinge implements WidgetComponent {
	HingeDefect minorDefect;
	HingeDefect majorDefect;
	HingeDefect massiveDefect;
	
	public Hinge() {}
	
	public Hinge(HingeDefect minorDefect, HingeDefect majorDefect, HingeDefect massiveDefect) {
		this.minorDefect = minorDefect;
		this.majorDefect = majorDefect;
		this.massiveDefect = massiveDefect;
	}

	public HingeDefect getMinorDefect() {
		return minorDefect;
	}
	
	public void setMinorDefect(HingeDefect minorDefect) {
		this.minorDefect = minorDefect;
	}
	
	public HingeDefect getMajorDefect() {
		return majorDefect;
	}
	
	public void setMajorDefect(HingeDefect majorDefect) {
		this.majorDefect = majorDefect;
	}

	public HingeDefect getMassiveDefect() {
		return massiveDefect;
	}
	
	public void setMassiveDefect(HingeDefect massiveDefect) {
		this.massiveDefect = massiveDefect;
	}

	@Override
	public Defect getDefect(DefectSeverity defectSeverity) {
		if (DefectSeverity.MINOR == defectSeverity) {
			return getMinorDefect();
		}
		else if (DefectSeverity.MAJOR == defectSeverity) {
			return getMajorDefect();
		}
		else if (DefectSeverity.MASSIVE == defectSeverity) {
			return getMassiveDefect();
		}
		else {
			return null;
		}
	}
}
