package com.gcu.business;

import java.util.List;

import com.gcu.model.PostModel;

public interface InterfacePostsBusinessService {
	
	public List<PostModel> getPosts();
	
	public PostModel getPostById(int id);
	
	public int saveNewPost(PostModel post);
	
	public int editPost(PostModel post);
	
	public int deletePost(PostModel post);
}
