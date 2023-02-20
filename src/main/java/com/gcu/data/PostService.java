package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.gcu.model.CredentialsModel;
import com.gcu.model.PostModel;
import com.gcu.model.UserModel;
//posts service
@Service
public class PostService implements PostsDataAccessInterface<PostModel> {

	//milestone 4 additions
	//data source and jdbc template object
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	
	
	//non-default constructor
	public PostService(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	//finds all the posts in the DB (get)
	@Override
	public List<PostModel> findAll() {
		String sql = "SELECT * FROM posts";
		List<PostModel> posts = new ArrayList<PostModel>();
		
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			while(srs.next()) {
				posts.add(new PostModel(srs.getInt("postID"),
						srs.getString("userName"),
						srs.getString("postName"),
						srs.getString("postBody")));
			}
			
		
		
		return posts;
	}

	//finds one post by ID (getByID)
	@Override
	public PostModel findById(int id) {
		String sql = "SELECT * FROM posts WHERE postID = '" + id + "'";
		PostModel post = new PostModel();

			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			while(srs.next()) {
				post = new PostModel(srs.getInt("postID"),
						srs.getString("userName"),
						srs.getString("postName"),
						srs.getString("postBody"));
			

		} 

		
		return post;
	}

	//saves a new post (create)
	@Override
	public int savePost(PostModel post) {
		String sql = "INSERT INTO posts(userName, postName, postBody) VALUES (?, ?, ?)";
			int rows = jdbcTemplateObject.update(sql, 
					post.getUserName(),
					post.getPostName(),
					post.getPostBody());
			return rows == 1 ? 1 : 0;
		
		//return 0;
	}

	//edit a post (update)
	@Override
    public int edit(PostModel t) {
        String sql = "UPDATE posts SET postID = ?, userName = ?, postName = ?, postBody = ? WHERE postID = '" + t.getPostID() + "'";

            int rows = jdbcTemplateObject.update(sql,
                    t.getPostID(),
                    t.getUserName(),
                    t.getPostName(),
                    t.getPostBody());
            
            System.out.println("post to be updated: " + t.getPostID() + t.getUserName() + t.getPostName() + t.getPostBody());
            return rows == 1 ? 1 : 0;
       

        //return 0;
    }

	//remove a post (delete)
    @Override
    public int remove(PostModel t) {
        String sql = "DELETE FROM posts WHERE postID = '" + t.getPostID() + "'";
        
        
            int rows = jdbcTemplateObject.update(sql);
            return rows == 1 ? 1: 0;
      
        //return 0;
    }
	

}
