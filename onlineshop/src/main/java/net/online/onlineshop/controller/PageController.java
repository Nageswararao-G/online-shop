package net.online.onlineshop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.backend.shopbackend.dao.CategoryDAO;
import net.backend.shopbackend.dao.ProductDAO;
import net.backend.shopbackend.dto.Category;
import net.backend.shopbackend.dto.Product;
import net.online.onlineshop.exception.ProductNotFoundException;

@Controller
public class PageController {

	private static final Logger logger = LoggerFactory.getLogger(PageController.class);

	@Autowired
	CategoryDAO categoryDAO;

	@Autowired
	ProductDAO productDAO;

	@RequestMapping(value = { "/", "/index", "/home" })
	public ModelAndView index() {

		logger.info("Inside page controller index method");
		logger.debug("Inside page controller index method");
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("title", "Home");
		mav.addObject("userClickHome", true);
		mav.addObject("categories", categoryDAO.categories());
		// mav.addObject("greeting", "Welcome to spring web MVC");
		return mav;
	}

	@RequestMapping(value = "/about")
	public ModelAndView about() {
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("title", "About Us");
		mav.addObject("userClickAbout", true);
		return mav;
	}

	@RequestMapping(value = "/contact")
	public ModelAndView contact() {
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("title", "Contact Us");
		mav.addObject("userClickContact", true);
		return mav;
	}

	/*
	 * Methods to load all the products and based on category
	 */
	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProducts() {
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("title", "All Products");
		mav.addObject("userClickAllProducts", true);
		mav.addObject("categories", categoryDAO.categories());
		return mav;
	}

	@RequestMapping(value = "/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {

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
	 * Viewing single product
	 */
	@RequestMapping(value = "/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException {

		ModelAndView mav = new ModelAndView("page");

		Product product = productDAO.get(id);
		if (product == null)
			throw new ProductNotFoundException();

		product.setViews(product.getViews() + 1);
		productDAO.update(product);

		mav.addObject("title", product.getName());
		mav.addObject("userClickShowProduct", true);
		mav.addObject("product", product);
		return mav;
	}

	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {

		ModelAndView mav = new ModelAndView("login");
		if (error != null) {
			mav.addObject("message", "Invalid credentials");
		}
		
		if (logout != null) {
			mav.addObject("logout", "User has successfully logged out");
		}

		mav.addObject("title", "login");
		// mav.addObject("userClickAllProducts", true);
		return mav;
	}

	@RequestMapping(value = "/access-denied")
	public ModelAndView accessDenied() {

		ModelAndView mav = new ModelAndView("error");
		mav.addObject("errorTitle", "Aha! Caught you");
		mav.addObject("errorDescription", "You are not authorized to view this page");
		mav.addObject("title", "403- Access Denied");
		// mav.addObject("userClickAllProducts", true);
		return mav;
	}
	
	@RequestMapping(value = "/perform-logout")
	public String logout(HttpServletRequest request,HttpServletResponse response) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
			return "redirect:login?logout";
	}
	
	

	/*
	 * @RequestMapping(value = "/test") public ModelAndView
	 * testRequestParam(@RequestParam(value="greeting", required=false)String
	 * greeting){ if(greeting == null){ greeting = "Hello there"; } ModelAndView
	 * mav = new ModelAndView("page"); mav.addObject("greeting", greeting);
	 * return mav; }
	 * 
	 * @RequestMapping(value = "/test/{greeting}") public ModelAndView
	 * testPathVariable(@PathVariable("greeting")String greeting){ ModelAndView
	 * mav = new ModelAndView("page"); mav.addObject("greeting", greeting);
	 * return mav; }
	 */

}
