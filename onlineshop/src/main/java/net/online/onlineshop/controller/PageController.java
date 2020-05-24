package net.online.onlineshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.backend.shopbackend.dao.CategoryDAO;
import net.backend.shopbackend.dto.Category;

@Controller
public class PageController {

	@Autowired
	CategoryDAO categoryDAO;
	
	@RequestMapping(value={"/","/index","/home"})
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("title", "Home");
		mav.addObject("userClickHome", true);
		mav.addObject("categories", categoryDAO.categories());
		//mav.addObject("greeting", "Welcome to spring web MVC");
		return mav;
	}
	
	@RequestMapping(value="/about")
	public ModelAndView about(){
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("title", "About Us");
		mav.addObject("userClickAbout", true);
		return mav;
	}
	
	@RequestMapping(value="/contact")
	public ModelAndView contact(){
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("title", "Contact Us");
		mav.addObject("userClickContact", true);
		return mav;
	}
	
	
	/*
	 * Methods to load  all the products and based on category
	 */
	@RequestMapping(value="/show/all/products")
	public ModelAndView showAllProducts(){
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("title", "All Products");
		mav.addObject("userClickAllProducts", true);
		mav.addObject("categories", categoryDAO.categories());
		return mav;
	}
	
	@RequestMapping(value="/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id){
		
		Category category = null;
		
		category = categoryDAO.get(id);
		
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("title", category.getName());
		mav.addObject("userClickCategoryProducts", true);
		mav.addObject("category", category);
		mav.addObject("categories", categoryDAO.categories());
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
