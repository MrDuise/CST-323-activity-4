package com.gcu.controller;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 *  Code referenced by Mark Reha's Global Exception Handler
 *
 */

@ControllerAdvice
public class GlobalExceptionHandler {
    
   /* @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex)
    {
        // Create a Model and View, populate with the Exception information, and display a common Error Page
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Error Page");
        model.addObject("error", "Error:  An Exception was not handled in the application: " + ex.getMessage());
        model.setViewName("error");
        return model;
    }  */
    
     @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model)
    {
       // model.addObject("title", "Error Page");
        model.addAttribute("error", "Error:  An Exception was not handled in the application: " + ex.getMessage());
       // model.setViewName("error");
        model.addAttribute("title", "Error Page");
        return "error";
    } 
    
   

}
