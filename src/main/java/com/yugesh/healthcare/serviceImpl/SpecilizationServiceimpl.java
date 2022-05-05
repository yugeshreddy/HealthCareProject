package com.yugesh.healthcare.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yugesh.healthcare.entity.Specilization;
import com.yugesh.healthcare.exception.SpecializationNotFoundException;
import com.yugesh.healthcare.repo.SpecilizationRepo;
import com.yugesh.healthcare.service.SpecilizationService;

@Service
public class SpecilizationServiceimpl implements SpecilizationService {
	@Autowired
	private SpecilizationRepo repo;

	@Override
	public Long saveSpecialization(Specilization spec) {
		Specilization specilization = repo.save(spec);
		return specilization.getId();
	}

	@Override
	public List<Specilization> getAllSpecializations() {
		List<Specilization> all = repo.findAll();
		return all;
	}

	@Override
	public void removeSpecialization(Long id) {
		repo.delete(getOneSpecialization(id));
	}

	@Override
	public Specilization getOneSpecialization(Long id) {
		/*
		 * Optional<Specilization> byId = repo.findById(id); if (byId.isPresent()) {
		 * return byId.get(); } else { throw new
		 * SpecializationNotFoundException(id+"not found"); }
		 */
		return repo.findById(id).orElseThrow(() -> new SpecializationNotFoundException(id + " Not found"));
	}

	@Override
	public void updateSpecialization(Specilization spec) {
		repo.save(spec);
	}

	@Override
	public boolean iscodeExist(String code) {

		return repo.getspecCodeCount(code) > 0;
	}

	@Override
	public boolean isNameExist(String name) {

		return repo.getspecnameCount(name) > 0;
	}

	@Override
	public boolean isSpecNameExistForEdit(String name, Long id) {
		return repo.getspecNameCountforEdit(name, id) > 0;
	}

	@Override
	public boolean isSpecCodeExistForEdit(String code, Long id) {
		return repo.getspecCodeCountforEdit(code, id) > 0;
	}

}
