package com.yugesh.healthcare.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yugesh.healthcare.entity.Specilization;

public interface SpecilizationRepo extends JpaRepository<Specilization, Long> {
	@Query("select count(specCode) from Specilization where specCode=:specCode")	
	Integer getspecCodeCount(String specCode);
	@Query("select count(specName) from Specilization where specName=:specName")
	Integer getspecnameCount(String specName);
	@Query("select count(specCode) from Specilization where specCode=:specCode and id!=:id")	
	Integer getspecCodeCountforEdit(String specCode, Long id);
	@Query("select count(specName) from Specilization where specName=:specName and id!=:id")
	Integer getspecNameCountforEdit(String specName, Long id);
}
