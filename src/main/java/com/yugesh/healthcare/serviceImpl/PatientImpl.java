package com.yugesh.healthcare.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yugesh.healthcare.constants.UserRoles;
import com.yugesh.healthcare.entity.Patient;
import com.yugesh.healthcare.entity.User;
import com.yugesh.healthcare.repo.PatientRepo;
import com.yugesh.healthcare.service.PatientService;
import com.yugesh.healthcare.service.UserService;
import com.yugesh.healthcare.util.MyMailutil;
import com.yugesh.healthcare.util.UserUtil;



/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
@Service
public class PatientImpl implements PatientService {
	@Autowired
	private PatientRepo repo;
	@Autowired
	private UserService userService;
	@Autowired
	private UserUtil util;

	@Autowired
	private MyMailutil mailUtil ;

	@Override
	@Transactional
	public Long savePatient(Patient patient) {
		Long id = repo.save(patient).getId();
		if(id!=null) {
			String pwd = util.genPwd();
			User user = new User();
			user.setDisplayName(patient.getFirstName()+" "+patient.getLastName());
			user.setUsername(patient.getEmail());
			user.setPassword(pwd);
			user.setRole(UserRoles.PATIENT.name());
			Long genId  = userService.saveUser(user);
			if(genId!=null)
				new Thread(new Runnable() {
					public void run() {
						String text = "Your name is " + patient.getEmail() +", password is "+ pwd;
						mailUtil.send(patient.getEmail(), "PATIENT ADDED", text);
					}
				}).start();
		}
		return id;
	}

	@Override
	@Transactional
	public void updatePatient(Patient patient) {
		repo.save(patient);
	}

	@Override
	@Transactional
	public void deletePatient(Long id) {
		repo.deleteById(id);
	}

	@Override
	@Transactional(
			readOnly = true
			)
	public Patient getOnePatient(Long id) {
		return repo.findById(id).get();
	}

	@Override
	@Transactional(
			readOnly = true
			)
	public List<Patient> getAllPatients() {
		return repo.findAll();
	}
	
	@Override
	public Patient getOneByEmail(String email) {
		return repo.findByEmail(email).get();
	}
	
	@Override
	public long getPatientCount() {
		return repo.count();
	}
}