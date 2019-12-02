package io.github.anantharajuc.sbtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewsController 
{
	@RequestMapping("/bootstrap")
	public String Bootstrap(Model model)
	{
		model.addAttribute("appName","Spring boot microservice app template");
		return "Bootstrap-View";
	}
	
	@RequestMapping("/angular")
	public String Angular(Model model)
	{
		model.addAttribute("appName","Spring boot microservice app template");
		return "Angular-View";
	}
	
	@RequestMapping("/material-design")
	public String MaterialDesign(Model model)
	{
		model.addAttribute("appName","Spring boot microservice app template");
		return "Material-Design-View";
	}
}
