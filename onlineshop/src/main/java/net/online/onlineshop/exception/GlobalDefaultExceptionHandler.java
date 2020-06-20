package net.online.onlineshop.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handleNoHandlerFoundException(){
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("errorTitle", "The Page is not constructed!");
		mav.addObject("errorDescription", "The Page you are looking for not available!");
		mav.addObject("title", "404 error page");
		return mav;
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handleProductNotFoundException(){
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("errorTitle", "Product not available!");
		mav.addObject("errorDescription", "The Product you are looking for not available right now!");
		mav.addObject("title", "Product unavailable");
		return mav;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception ex){
		ModelAndView mav = new ModelAndView("error");
		
		// only for debugging your application not for production
		StringWriter sw = new StringWriter();
		PrintWriter pw =new PrintWriter(sw);
		ex.printStackTrace(pw);
		
		
		mav.addObject("errorTitle", "contact Administrator!");
		mav.addObject("errorDescription", sw.toString());
		mav.addObject("title", "Error");
		return mav;
	}
}
