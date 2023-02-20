package com.gcu.data;

import java.util.List;

import com.gcu.model.CredentialsModel;
import com.gcu.model.UserModel;

public interface PostsDataAccessInterface <T> {
	public List<T> findAll();
	public T findById(int id);
	public int savePost(T t);
	public int edit(T t);
	public int remove(T t);
	
}
