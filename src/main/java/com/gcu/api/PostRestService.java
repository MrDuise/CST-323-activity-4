package com.gcu.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.business.PostsBusinessService;
import com.gcu.model.PostModel;

@RestController
@RequestMapping("/service")
public class PostRestService {

	@Autowired
	PostsBusinessService service;
	
	/**
	 * returns all the posts from the database
	 * @return
	 */
	@GetMapping(path="/posts")
	public ResponseEntity<?> getPosts() {
		try {
			List<PostModel> posts = service.getPosts();
			if(posts == null) 
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			 else 
				return new ResponseEntity<>(posts, HttpStatus.OK);
		}
			catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
	}
	/**
	 * 
	 * @param id -  the id of the post to be found
	 * @return -  a 404 not found error if the post does not exist otherwise returns the 
	 */
	@GetMapping(path="/getOnePost/{id}")
	public ResponseEntity<?> getOnePost(@PathVariable("id") int id) {
		//using the path variable of id, find the post that it belongs to and return it
		try {
			PostModel post = service.getPostById(id);
			//if the post does not exist 
			if(post == null) 
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			 else 
				return new ResponseEntity<>(post, HttpStatus.OK);
		}
			catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
	}
}
