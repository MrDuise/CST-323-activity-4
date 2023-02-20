package com.gcu.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.data.PostsDataAccessInterface;
import com.gcu.model.PostModel;

@Service
public class PostsBusinessService implements InterfacePostsBusinessService {
	
	@Autowired
	PostsDataAccessInterface<PostModel> service;

	@Override
	public List<PostModel> getPosts() {
		return service.findAll();
	}

	@Override
	public PostModel getPostById(int id) {
		return service.findById(id);
	}

	@Override
	public int saveNewPost(PostModel post) {
		return service.savePost(post);
	}

	@Override
	public int editPost(PostModel post) {
		return service.edit(post);
	}

	@Override
	public int deletePost(PostModel post) {
		return service.remove(post);
	}
	

}
