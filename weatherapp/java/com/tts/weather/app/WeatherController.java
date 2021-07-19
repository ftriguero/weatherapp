package com.tts.weather.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WeatherController 
{
	//Inversion of control. lifecyles managed by Spring Boot, not by the programmer
	
	@Autowired
	private WeatherService weatherService;
	
	//Handle all request to "/"
	@GetMapping(value="/")
	public String getIndex(Model model)
	{
		model.addAttribute("last", weatherService.getLastEntries());
		model.addAttribute("request", new Request("91732"));
		return "index";
	}
	
	@PostMapping(value="/")
	public String postIndex(Request request, Model model)
	{
		Response data = weatherService.getForecast(request.getZipCode());
		model.addAttribute("last", weatherService.getLastEntries());
		model.addAttribute("data", data);
		return "index";
	}
}
