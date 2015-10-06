package com.jmain.websvc.widget.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jmain.websvc.widget.model.Casing;
import com.jmain.websvc.widget.model.Defect;
import com.jmain.websvc.widget.model.DefectSeverity;
import com.jmain.websvc.widget.model.Hinge;
import com.jmain.websvc.widget.model.Sprocket;
import com.jmain.websvc.widget.model.Tier;
import com.jmain.websvc.widget.model.Widget;
import com.jmain.websvc.widget.model.WidgetComponent;
import com.jmain.websvc.widget.model.WidgetGeneration;

@Service
public class WidgetService {
	public Tier evaluateWidget(Widget widget) {
		if (widget.getFrontCasing() == null || widget.getRearCasing() == null
				|| widget.getHinge() == null) {
			return Tier.TIER_MINUS_ONE;
		}
		
		Map<DefectSeverity, Integer> widgetDefects = buildDefectCountMap();
		
		Defect frontCasingDefect = evaluateWidgetComponent(widget.getFrontCasing());
		widgetDefects = updateWidgetDefects(widgetDefects, frontCasingDefect);
		
		Defect rearCasingDefect = evaluateWidgetComponent(widget.getRearCasing());
		widgetDefects = updateWidgetDefects(widgetDefects, rearCasingDefect);
		
		Defect hingeDefect = evaluateWidgetComponent(widget.getHinge());
		widgetDefects = updateWidgetDefects(widgetDefects, hingeDefect);
		
		for (Sprocket sprocket : widget.getSprockets()) {
			Defect sprocketDefect = evaluateWidgetComponent(sprocket);
			widgetDefects = updateWidgetDefects(widgetDefects, sprocketDefect);
		}
		
		adjustDefectCountsByGeneration(widget.getGeneration(), widgetDefects);
		
		return determineTier(widgetDefects);
	}
	
	private Map<DefectSeverity, Integer> buildDefectCountMap() {
		Map<DefectSeverity, Integer> defectCounts = new HashMap<>();
		defectCounts.put(DefectSeverity.MINOR, 0);
		defectCounts.put(DefectSeverity.MAJOR, 0);
		defectCounts.put(DefectSeverity.MASSIVE, 0);
		
		return defectCounts;
	}
	
	private Defect evaluateWidgetComponent(WidgetComponent component) {
		Defect massiveDefect = component.getDefect(DefectSeverity.MASSIVE);
		if (massiveDefect != null) {
			return massiveDefect;
		}
		Defect majorDefect = component.getDefect(DefectSeverity.MAJOR);
		if (majorDefect != null) {
			return majorDefect;
		}
		Defect minorDefect = component.getDefect(DefectSeverity.MINOR);
		if (minorDefect != null) {
			return minorDefect;
		}
		else {
			return null;
		}
	}
	
	private Map<DefectSeverity, Integer> updateWidgetDefects(Map<DefectSeverity, Integer> widgetDefects,
			Defect defect) {
		if (defect != null) {
			int defectCount = widgetDefects.get(defect.getDefectSeverity()) + 1;
			widgetDefects.put(defect.getDefectSeverity(), defectCount);
		}
		
		return widgetDefects;
	}

	private Map<DefectSeverity, Integer> adjustDefectCountsByGeneration(WidgetGeneration widgetGeneration, 
			Map<DefectSeverity, Integer> componentDefects) {		
		int adjustedDefects = componentDefects.get(DefectSeverity.MINOR).intValue() - widgetGeneration.minorIgnored();
		if (adjustedDefects > 0) {
			componentDefects.put(DefectSeverity.MINOR, adjustedDefects);
		}
		else {
			componentDefects.put(DefectSeverity.MINOR, 0);
		}
		
		adjustedDefects = componentDefects.get(DefectSeverity.MAJOR).intValue() - widgetGeneration.majorIgnored();
		if (adjustedDefects > 0) {
			componentDefects.put(DefectSeverity.MAJOR, adjustedDefects);
		}
		else {
			componentDefects.put(DefectSeverity.MAJOR, 0);
		}
		
		adjustedDefects = componentDefects.get(DefectSeverity.MASSIVE).intValue() - widgetGeneration.massiveIgnored();
		if (adjustedDefects > 0) {
			componentDefects.put(DefectSeverity.MASSIVE, adjustedDefects);
		}
		else {
			componentDefects.put(DefectSeverity.MASSIVE, 0);
		}
		
		return componentDefects;
	}
	
	private Tier determineTier(Map<DefectSeverity, Integer> defectCounts) {
		final int minorDefects = defectCounts.get(DefectSeverity.MINOR);
		final int majorDefects = defectCounts.get(DefectSeverity.MAJOR);
		final int massiveDefects = defectCounts.get(DefectSeverity.MASSIVE);

		if (massiveDefects <= Tier.TIER_FIVE.massiveMaxAllowed()) {
			if (majorDefects <= Tier.TIER_FIVE.majorMaxAllowed()) {
				if (minorDefects <= Tier.TIER_FIVE.minorMaxAllowed()) {
					return Tier.TIER_FIVE;
				}
			}
		}

		if (massiveDefects <= Tier.TIER_FOUR.massiveMaxAllowed()) {
			if (majorDefects <= Tier.TIER_FOUR.majorMaxAllowed()) {
				if (minorDefects <= Tier.TIER_FOUR.minorMaxAllowed()) {
					return Tier.TIER_FOUR;
				}
			}
		}

		if (massiveDefects <= Tier.TIER_THREE.massiveMaxAllowed()) {
			if (majorDefects <= Tier.TIER_THREE.majorMaxAllowed()) {
				if (minorDefects <= Tier.TIER_THREE.minorMaxAllowed()) {
					return Tier.TIER_THREE;
				}
			}
		}

		if (massiveDefects <= Tier.TIER_TWO.massiveMaxAllowed()) {
			if (majorDefects <= Tier.TIER_TWO.majorMaxAllowed()) {
				if (minorDefects <= Tier.TIER_TWO.minorMaxAllowed()) {
					return Tier.TIER_TWO;
				}
			}
		}

		if (massiveDefects <= Tier.TIER_ONE.massiveMaxAllowed()) {
			if (majorDefects <= Tier.TIER_ONE.majorMaxAllowed()) {
				if (minorDefects <= Tier.TIER_ONE.minorMaxAllowed()) {
					return Tier.TIER_ONE;
				}
			}
		}

		return Tier.TIER_ZERO;
	}
}
