package com.gcu.model;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PostModel {
	private int postID;
	private String userName;
	
	@NotNull(message="Post Name is a required field")
	@Size(min=1, max=100, message="Post Name must be between 1 and 100 characters")
	private String postName;
	
	@NotNull(message="Post Body is a required field")
	@Size(min=1, max=500, message="Post Body must be between 1 and 500 characters")
	private String postBody;
	
	private ArrayList<String> comments;
	
	//default constructor
	
	public PostModel() {
		
	}
	public PostModel(@NotNull(message = "Post Name is a required field") @Size(min = 1, max = 100, message = "Post Name must be between 1 and 100 characters") String postName, @NotNull(message = "Post Body is a required field") @Size(min = 1, max = 500, message = "Post Body must be between 1 and 500 characters") String postBody) {
			this.postName = postName;
			this.postBody = postBody;
		
	}
	
	//post with no comments
	public PostModel(int postID, String userName, String postName, String postBody) {
		
		this.postID = postID;
		this.userName = userName;
		this.postName = postName;
		this.postBody = postBody;
	}
	
	//post with no comments
		public PostModel(int postID, String userName, String postName, String postBody, String comments) {
			
			this.postID = postID;
			this.userName = userName;
			this.postName = postName;
			this.postBody = postBody;
			
		}

	//post that already has comments wow 
	public PostModel(int postID, String userName, String postName, String postBody, ArrayList<String> comments) {
		
		this.postID = postID;
		this.userName = userName;
		this.postName = postName;
		this.postBody = postBody;
		this.comments = comments;
	}
	//post that already has comments wow 
	public PostModel(String userName, String postName, String postBody) {

		this.userName = userName;
		this.postName = postName;
		this.postBody = postBody;
	}


	public int getPostID() {
		return postID;
	}
	public void setPostID(int postID) {
		this.postID = postID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserID(String userName) {
		this.userName = userName;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getPostBody() {
		return postBody;
	}
	public void setPostBody(String postBody) {
		this.postBody = postBody;
	}
	public ArrayList<String> getComments() {
		return comments;
	}
	public void setComments(ArrayList<String> comments) {
		this.comments = comments;
	}
	@Override
	public String toString() {
		return "PostModel [postID=" + postID + ", userName=" + userName + ", postName=" + postName + ", postBody="
				+ postBody + ", comments=" + comments + "]";
	}
    
	
	
	
	
}
