package com.yugesh.healthcare.runner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.yugesh.healthcare.constants.UserRoles;
import com.yugesh.healthcare.entity.User;
import com.yugesh.healthcare.service.UserService;
import com.yugesh.healthcare.util.MyMailutil;
import com.yugesh.healthcare.util.UserUtil;



@Component
public class MasterAccountSetupRunner implements CommandLineRunner {

	@Value("${master.user.name}")
	private String displayName;
	
	@Value("${master.user.email}")
	private String username;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserUtil userUtil;
	
	@Autowired
	private MyMailutil mailUtil ;
	
	public void run(String... args) throws Exception {
		if(!userService.findByUsername(username).isPresent()) {
			String pwd = userUtil.genPwd();
			User user = new User();
			user.setDisplayName(displayName);
			user.setUsername(username);
			user.setPassword(pwd);
			user.setRole(UserRoles.ADMIN.name());
			Long genId  = userService.saveUser(user);
			if(genId!=null)
				new Thread(new Runnable() {
					public void run() {
						String text = "Your uname is " + username +", password is "+ pwd;
						mailUtil.send(username, "ADMIN ADDED", text);
					}
				}).start();
		}
	}	

}
