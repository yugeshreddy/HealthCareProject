package com.yugesh.healthcare.service;

import java.util.List;

import com.yugesh.healthcare.entity.Specilization;

public interface SpecilizationService {
	
	public Long saveSpecialization(Specilization spec);
	public List<Specilization> getAllSpecializations();
	public void removeSpecialization(Long id);
	public Specilization getOneSpecialization(Long id);
	public void updateSpecialization(Specilization spec);
	public boolean iscodeExist(String code);
	public boolean isNameExist(String name);
	public boolean isSpecNameExistForEdit(String name, Long id);
	public boolean isSpecCodeExistForEdit(String code, Long id);

}
