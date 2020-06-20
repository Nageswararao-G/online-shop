package net.online.onlineshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.backend.shopbackend.dao.CategoryDAO;
import net.backend.shopbackend.dao.ProductDAO;
import net.backend.shopbackend.dto.Category;
import net.backend.shopbackend.dto.Product;
import net.online.onlineshop.util.FileUploadUtility;
import net.online.onlineshop.validator.ProductValidator;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name = "operation", required = false) String operation) {

		ModelAndView mav = new ModelAndView("page");
		mav.addObject("userClickManageProducts", true);
		mav.addObject("title", "Manage Products");

		Product nProduct = new Product();

		// assuming that the user is ADMIN
		// later we will fixed it based on user is SUPPLIER or ADMIN
		nProduct.setSupplierId(1);
		nProduct.setActive(true);

		mav.addObject("product", nProduct);

		if (operation != null) {
			if (operation.equals("product")) {
				mav.addObject("message", "Product submitted successfully");
			}else if (operation.equals("category")) {
				mav.addObject("message", "Category submitted successfully");
			}
			
		}
		return mav;

	}

	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public String handleProductubmission(@Valid @ModelAttribute("product") Product mProduct,BindingResult results,Model model,HttpServletRequest request) {
		
		if(mProduct.getId() == 0){
		new ProductValidator().validate(mProduct, results);
		}else{
			if(!mProduct.getFile().getOriginalFilename().equals("")){
				new ProductValidator().validate(mProduct, results);
			}
		}
		
		if(results.hasErrors()){
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title", "Manage Products");
			return "page";
		}

		logger.info(mProduct.toString());
		
		if(mProduct.getId() == 0){
		    productDAO.add(mProduct);
		}else{
			productDAO.update(mProduct);
		}
		
		if(!mProduct.getFile().getOriginalFilename().equals("")){
			FileUploadUtility.upload(request, mProduct.getFile(),mProduct.getCode());
		}
		
		return "redirect:/manage/products?operation=product";

	}

	@ModelAttribute("categories")
	public List<Category> modelCategories() {
		return categoryDAO.categories();
	}
	
	@RequestMapping(value = "/product/{id}/activation", method = RequestMethod.GET)
	@ResponseBody
	public String handleProductActivationubmission(@PathVariable int id) {
		
		Product product = productDAO.get(id);
		boolean isActive = product.isActive();
		
		product.setActive(!isActive);
		productDAO.update(product);
		return (isActive) ? "You are successfully deactivated the product with id " +product.getId() : 
			                "You are successfully activated the product with id " +product.getId();

	}
	
	@RequestMapping(value = "/{id}/product", method = RequestMethod.GET)
	public ModelAndView showEditProducts(@PathVariable int id) {

		ModelAndView mav = new ModelAndView("page");
		mav.addObject("userClickManageProducts", true);
		mav.addObject("title", "Manage Products");

		Product nProduct = productDAO.get(id);
 
		mav.addObject("product", nProduct);

		return mav;

	}
	
	@ModelAttribute("category")
	public Category getCategory() {
		return new Category();
	}
	
	
	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category) {
	
		category.setActive(true);;
		categoryDAO.add(category);
		return "redirect:/manage/products?operation=category";

	}

}
