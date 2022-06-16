package com.yugesh.healthcare.serviceImpl;

import java.util.Optional;
import java.util.Collections;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.yugesh.healthcare.entity.User;
import com.yugesh.healthcare.repo.UserRepository;
import com.yugesh.healthcare.service.UserService;


@Service
public class Userimpl implements UserService,UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository repo;

	public Long saveUser(User user) {
		//read generated pwd
		String pwd = user.getPassword();
		System.out.println("password "+pwd);
		//enode password
		String encPwd = passwordEncoder.encode(pwd);
		//set back to object
		user.setPassword(encPwd);

		return repo.save(user).getId();
	}
	
	@Transactional
	public void updateUserPwd(String pwd, Long userId) {
		String encPwd = passwordEncoder.encode(pwd);
		repo.updateUserPwd(encPwd, userId);
	}

	public Optional<User> findByUsername(String username) {
		return repo.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		Optional<User> opt = findByUsername(username);
		System.out.println("yugesh"+username);
		if(!opt.isPresent()) 
			throw new UsernameNotFoundException(username);
		else {
			User user = opt.get();
			System.out.println("user"+user);
			org.springframework.security.core.userdetails.User user2 = new org.springframework.security.core.userdetails.User(
					user.getUsername(), 
					user.getPassword(), 
					Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))
					);
			System.out.println("secuity object+user2"+user2.getPassword());
			return user2;
			
		}
	}



}
