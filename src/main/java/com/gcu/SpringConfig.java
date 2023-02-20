package com.gcu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.gcu.business.InterfacePostsBusinessService;
import com.gcu.business.InterfaceRegisterBusinessService;
import com.gcu.business.PostsBusinessService;
import com.gcu.business.RegisterBusinessService;



//SpringConfig class
@Configuration
public class SpringConfig {

	
	/* //spring bean definition
	@Bean(name="postsBusinessService")
	public InterfacePostsBusinessService getPostsBusiness() {
		return new PostsBusinessService();
	}  */
	
	@Bean(name="registerBusinessService")
	public InterfaceRegisterBusinessService registerBusiness() {
		return new RegisterBusinessService();
	}

}
