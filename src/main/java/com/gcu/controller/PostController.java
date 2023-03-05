package com.gcu.controller;


import java.security.Principal;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.business.InterfacePostsBusinessService;
import com.gcu.business.UserBusinessService;
import com.gcu.model.PostModel;
import com.gcu.model.UserModel;

/**
 * Post controller. Handles displaying the create post view
 * 
 * @author Michael, Nicole
 *
 */

@Controller
@RequestMapping("/posts")
public class PostController {

   
    // Milestone 3: injected and autowired PostsBusinessService
    @Autowired
    private InterfacePostsBusinessService service;
    
    @Autowired
    private UserBusinessService userService;
    
    Logger logger = LoggerFactory.getLogger(getClass());

    
    @GetMapping("/displayPosts")
    public ModelAndView displayAllPosts() {
    	return new ModelAndView("posts", Map.of("title", "My Posts", "posts", service.getPosts()));
    }
    
    
    /**
     * Function is called when edit post form is submitted, if data has errors,
     * displays
     * the same page with errors; if data meets the valid requirements, checks that
     * data entered matches data needed to enter site
     * 
     * @param post     - is passed from the form, holds the values that the
     *                      users enters in the form
     * @param bindingResult
     * @param model
     * @return 
     */
    @PostMapping("/displayById")
    public String displayPostById(@Valid PostModel POST, BindingResult result, Model model) {
    	
    	logger.info("Entering PostController.displayPostById");

    	try {
    		PostModel post = service.getPostById(POST.getPostID());
    		model.addAttribute("post", post);
    		return "displaypostbyid";
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return "posts";
    	}
    }
    
    /**
     * Function is called by when the user goes to the URL of '/createpost/'
     * 
     * @param model
     * @return createpost
     */
    @GetMapping("/createpost")
    @PostMapping("/createpost")
    public String display(Model model) {
    	
    	logger.info("Entering PostController.display");

        model.addAttribute("title", "Create Post");
        model.addAttribute("postModel", new PostModel());
        return "createpost";
    }
    
    
    /**
     * Function is called when create post form is submitted, if data has errors,
     * displays
     * the same page with errors; if data meets the valid requirements, checks that
     * data entered matches data needed to enter site
     * 
     * @param postModel     - is passed from the form, holds the values that the
     *                      users enters in the form
     * @param bindingResult
     * @param model
     * @return createpost or posts
     */
    @PostMapping("/doCreate")
    public String makePost(@Valid PostModel postModel, BindingResult bindingResult, Model model, Principal principal) {
        // print the form values out
        System.out.println(String.format("form with post name of %s and post body of %s", postModel.getPostName(),
                postModel.getPostBody()));
    	
    	
    	logger.info(String.format("form with post name of %s and post body of %s", postModel.getPostName(),postModel.getPostBody()));

        // print data validation errors to the screen
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Create Post");

            return "createpost";
        }
        
        //add find by username function
        System.out.println(principal.getName());
        UserModel user = userService.findByUserName(principal.getName());

        // create new post save to database
        service.saveNewPost(new PostModel(user.getCredentials().getUsername(), postModel.getPostName(), postModel.getPostBody()));

        // display posts view upon successful creation of posts
        model.addAttribute("title", "My Posts");
        model.addAttribute("posts", service.getPosts());
        return "posts";
    }

    
    /**
     * Displays the update form for a post only if a valid ID is entered
     * 
     * @param id
     * @param model
     * @return edit post or posts
     */
    @PostMapping("/edit")
    public String showUpdateForm(@Valid PostModel POST, BindingResult result, Model model) {
    	
    	logger.info("Entering update form");

        try {

            PostModel post = service.getPostById(POST.getPostID());
            System.out.println(post);
            model.addAttribute("post", post);
            
            return "editpost";
        } catch (Exception e) {
        	if (result.hasErrors()) {
                model.addAttribute("title", "Edit Post");

                return "editpost";
            }
            e.printStackTrace();
            
        }
        return "posts";
    }
    
    /**
     * Function is called when edit post form is submitted, if data has errors,
     * displays
     * the same page with errors; if data meets the valid requirements, checks that
     * data entered matches data needed to enter site
     * 
     * @param post     - is passed from the form, holds the values that the
     *                      users enters in the form
     * @param bindingResult
     * @param model
     * @return editpost or posts
     */
    @PostMapping("/update")
    public String updatePost(@Valid PostModel post, BindingResult result, Model model) {

        // print data validation errors to the screen
        if (result.hasErrors()) {
            model.addAttribute("title", "Edit Post");

            return "editpost";
        }
//System.out.println(post);
        // edit an existing post
        service.editPost(post);
        
        logger.info(String.format("this is the post %s", post));

        // display posts view upon successful creation of posts
        model.addAttribute("title", "My Posts");
        model.addAttribute("posts", service.getPosts());
        return "posts";
    }


    /**
     * Function is called by when the user goes to the URL of '/delete/{id}'
     * 
     * @param model
     * @return deletepost or posts
     */
    @PostMapping("/delete")
    public String displayDelete(@Valid PostModel POST, BindingResult result, Model model) {
    	
    	logger.info("Entering PostController.displayDelete");
        
        try {
            PostModel post = service.getPostById(POST.getPostID());
            model.addAttribute("post", post);
            return "deletepost";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "posts";
    }
    
    
    /**
     * Function is called when delete post form is submitted, if data has errors,
     * displays
     * the same page with errors; if data meets the valid requirements, checks that
     * data entered matches data needed to enter site
     * 
     * @param postModel     - is passed from the form, holds the values that the
     *                      users enters in the form
     * @param bindingResult
     * @param model
     * @return deletepost or posts
     */
    @PostMapping("/deletepost/doDelete")
    public String deletePost(@Valid PostModel postModel, BindingResult bindingResult, Model model) {
        // print the form values out
        System.out.println(postModel);

     // print data validation errors to the screen
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Delete Post");

            return "deletepost";
        }

        // edit an existing post
        service.deletePost(postModel);

        // display posts view upon successful creation of posts
        model.addAttribute("title", "My Posts");
        model.addAttribute("posts", service.getPosts());
        return "posts";
    }
    
}
