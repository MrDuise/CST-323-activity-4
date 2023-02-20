package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.gcu.model.CredentialsModel;
import com.gcu.model.PostModel;
import com.gcu.model.UserModel;

//users service 
@Service
public class UsersService implements CredentialsDataAccessInterface <UserModel> {
    
    
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    
    //non-default constructor
    public UsersService(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    //user login processes the login of an existing user
    @Override
    public UserModel login(CredentialsModel credentialsModel) {
        String sql = "SELECT * FROM users WHERE username = '" + credentialsModel.getUsername() + " ' AND password = '" + credentialsModel.getPassword() + " ' ";
        UserModel user = new UserModel();
        
            
            SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);
            SqlRowSet src = jdbcTemplateObject.queryForRowSet(sql);
            while(src.next()) {
                
                user = new UserModel(src.getInt("userID"), credentialsModel ,src.getString("firstName"), src.getString("lastName"), src.getString("email"), src.getString("phoneNumber") );
            }
       
            //ex.printStackTrace();
        
        return user;
    }

    //register method handles the registration of a new user
    @Override
    public int register(UserModel userModel) {
        String sql = "INSERT INTO users(username, password, firstName, lastName, email, phoneNumber) VALUES(?,?,?,?,?,?)";

            //execute SQL Insert
            int rows = jdbcTemplateObject.update(sql,userModel.getCredentials().getUsername(), userModel.getCredentials().getPassword(), userModel.getFirstName(), userModel.getLastName(), userModel.getEmail(), userModel.getPhoneNumber());
            return rows == 1 ? 1 : 0;
        
    
            //e.printStackTrace();
        
        //return 0;
    }

    @Override
    public UserModel findByUserName(String username) {
        String sql = "SELECT * FROM users WHERE username = '" + username + " ' ";
        UserModel user = new UserModel();
            
            SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);
            SqlRowSet src = jdbcTemplateObject.queryForRowSet(sql);
            while(src.next()) {
                CredentialsModel cred = new CredentialsModel(src.getString("userName"), src.getString("password"));
                
                user = new UserModel(src.getInt("userID"), cred, src.getString("firstName"), src.getString("lastName"), src.getString("email"), src.getString("phoneNumber") );
            }

            //ex.printStackTrace();
        
        return user;
    }

    //added in milestone 7
    //get ALL users from the database
	@Override
	public List<UserModel> findAllUsers() {
		String sql = "SELECT * FROM USERS";
		List<UserModel> listOfUsers = new ArrayList<UserModel>();
            
            SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(listOfUsers);
            SqlRowSet src = jdbcTemplateObject.queryForRowSet(sql);
            while(src.next()) {
                CredentialsModel cred = new CredentialsModel(src.getString("userName"), src.getString("password"));
                listOfUsers.add(new UserModel(src.getInt("userID"), cred, src.getString("firstName"), src.getString("lastName"), src.getString("email"), src.getString("phoneNumber")));
                //user = new UserModel(src.getInt("userID"), cred, src.getString("firstName"), src.getString("lastName"), src.getString("email"), src.getString("phoneNumber") );
            }

            //ex.printStackTrace();
        
        return listOfUsers;
	}
    
    

}
