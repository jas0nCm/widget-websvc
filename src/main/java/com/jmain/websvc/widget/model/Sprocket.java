package com.jmain.websvc.widget.model;

public class Sprocket implements WidgetComponent {
	SprocketDefect minorDefect;
	SprocketDefect majorDefect;
	SprocketDefect massiveDefect;
	
	public Sprocket() {}
	
	public Sprocket(SprocketDefect minorDefect, SprocketDefect majorDefect, SprocketDefect massiveDefect) {
		this.minorDefect = minorDefect;
		this.majorDefect = majorDefect;
		this.massiveDefect = massiveDefect;
	}

	public SprocketDefect getMinorDefect() {
		return minorDefect;
	}
	
	public void setMinorDefect(SprocketDefect minorDefect) {
		this.minorDefect = minorDefect;
	}
	
	public SprocketDefect getMajorDefect() {
		return majorDefect;
	}
	
	public void setMajorDefect(SprocketDefect majorDefect) {
		this.majorDefect = majorDefect;
	}

	public SprocketDefect getMassiveDefect() {
		return massiveDefect;
	}
	
	public void setMassiveDefect(SprocketDefect massiveDefect) {
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
