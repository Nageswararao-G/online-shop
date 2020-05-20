package net.online.onlineshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

	@RequestMapping(value={"/","/index","/home"})
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("page");
		
		mav.addObject("greeting", "Welcome to spring web MVC");
		return mav;
	}
	
	/*
	@RequestMapping(value = "/test")
	public ModelAndView testRequestParam(@RequestParam(value="greeting", required=false)String greeting){
		if(greeting == null){
			greeting = "Hello there";
		}
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("greeting", greeting);
		return mav;
	}
	
	@RequestMapping(value = "/test/{greeting}")
	public ModelAndView testPathVariable(@PathVariable("greeting")String greeting){
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("greeting", greeting);
		return mav;
	}
	*/
	
}
