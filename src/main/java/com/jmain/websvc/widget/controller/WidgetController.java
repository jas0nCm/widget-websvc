package com.jmain.websvc.widget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jmain.websvc.widget.model.Tier;
import com.jmain.websvc.widget.model.Widget;
import com.jmain.websvc.widget.service.WidgetService;

@RestController
@RequestMapping(value="/websvc")
public class WidgetController {
	@Autowired 
	WidgetService widgetService;

	@RequestMapping(value="/tier", method=RequestMethod.POST, consumes="application/json")
	public Tier checkWidget(@RequestBody Widget widget) {
		return widgetService.evaluateWidget(widget);
	}
}
