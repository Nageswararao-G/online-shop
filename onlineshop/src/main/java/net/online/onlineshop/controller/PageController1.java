package net.online.onlineshop.controller;

import org.springframework.stereotype.Controller;

@Controller
public class PageController1 {/*

	@Autowired
	private CategoryDAO categoryDAO; 
	
	@RequestMapping(value = {"/","/home","/index"})
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("title", "Home");
		mav.addObject("categories", categoryDAO.list());
		mav.addObject("userClickHome", true);
		return mav;
	}
	
	@RequestMapping(value = "/about")
	public ModelAndView about(){
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("title", "About Us");
		mav.addObject("userClicAbout", true);
		return mav;
	}
	
	@RequestMapping(value = "/contact")
	public ModelAndView contact(){
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("title", "Contact Us");
		mav.addObject("userClicContact", true);
		return mav;
	}
	
	@RequestMapping(value = "/test")
	public ModelAndView test(@RequestParam(value="greeting",required=false)String greeting){
		if(greeting == null){
			greeting = "Hello there";
		}
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("greeting", greeting);
		return mav;
	}
	
	
	@RequestMapping(value = "/test/{greeting}")
	public ModelAndView test(@PathVariable("greeting")String greeting){
		
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("greeting", greeting);
		return mav;
	}
	
	
*/}
