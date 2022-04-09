package com.city.explorer.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;



@ControllerAdvice
public class GlobalControllerAdvice {
	
	public static final String DEFAULT_ERROR_VIEW = "error";
//	public static final String ADMIN_ERROR = "adminError";
		
	/*
	 * @InitBinder public void initBinder(WebDataBinder dataBinder) {
	 * StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
	 * dataBinder.registerCustomEditor(String.class, stringTrimmerEditor); }
	 */
	
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
			throw e;		
		ModelAndView mav = new ModelAndView();
		mav.addObject("timestamp", new Date(System.currentTimeMillis()));
		mav.addObject("path", req.getRequestURL());
		mav.addObject("message", e.getMessage());
		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
	}
	
	
	/*
	 * @ExceptionHandler(value = Exception.class) public ModelAndView
	 * adminErrorHandler(HttpServletRequest req, Exception e) throws Exception { if
	 * (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
	 * throw e; ModelAndView mav = new ModelAndView(); mav.addObject("timestamp",
	 * new Date(System.currentTimeMillis())); mav.addObject("path",
	 * req.getRequestURL()); mav.addObject("message", e.getMessage());
	 * mav.setViewName(ADMIN_ERROR); return mav; }
	 */
	
	
}
