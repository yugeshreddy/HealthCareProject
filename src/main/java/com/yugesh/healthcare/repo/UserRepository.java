package com.yugesh.healthcare.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.yugesh.healthcare.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
	//@Query("SELECT username,password,role FROM User WHERE username=:username")
	//@Query("SELECT usr FROM User usr WHERE usr.username=:username")
	Optional<User> findByUsername(String username);
	//List<Object> findByUsername(String username);

	
	@Modifying
	@Query("UPDATE User SET password=:encPwd WHERE id=:userId")
	void updateUserPwd(String encPwd,Long userId);
}
