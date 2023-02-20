package com.gcu.data;

import java.util.List;

import com.gcu.model.CredentialsModel;
import com.gcu.model.UserModel;

public interface CredentialsDataAccessInterface <T> {
    public UserModel login(CredentialsModel credentialsModel);
    public int register(UserModel userModel);
    
    public UserModel findByUserName(String username);
    
    //added in milestone 7
    public List<UserModel> findAllUsers();
}
