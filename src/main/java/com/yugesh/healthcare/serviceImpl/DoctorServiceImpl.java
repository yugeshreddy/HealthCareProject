package com.yugesh.healthcare.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yugesh.healthcare.constants.UserRoles;
import com.yugesh.healthcare.entity.Doctor;
import com.yugesh.healthcare.entity.User;
import com.yugesh.healthcare.exception.DoctorNotFoundException;
import com.yugesh.healthcare.repo.DoctorRepo;
import com.yugesh.healthcare.service.DoctorService;
import com.yugesh.healthcare.service.UserService;
import com.yugesh.healthcare.util.MyCollectionsUtil;
import com.yugesh.healthcare.util.MyMailutil;
import com.yugesh.healthcare.util.UserUtil;
@Service
public class DoctorServiceImpl implements DoctorService{
	@Autowired
	private DoctorRepo repo;
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserUtil util;

	@Autowired
	private MyMailutil mailUtil ;

	@Override
	public Long saveDoctor(Doctor doc) {
		
		Long id = repo.save(doc).getId();
		if(id!=null) {
			String pwd = util.genPwd();
			User user = new User();
			user.setDisplayName(doc.getFirstName()+" "+doc.getLastName());
			user.setUsername(doc.getEmail());
			user.setPassword(pwd);
			user.setRole(UserRoles.DOCTOR.name());
			Long genId  = userService.saveUser(user);
			if(genId!=null)
				new Thread(new Runnable() {
					public void run() {
						String text = "Your name is " + doc.getEmail() +", password is "+ pwd;
						mailUtil.send(doc.getEmail(), "PATIENT ADDED", text);
					}
				}).start();
		}
		return id;
	}

	@Override
	public List<Doctor> getAllDoctors() {
		return repo.findAll();
	}

	@Override
	public void removeDoctor(Long id) {
		repo.delete(getOneDoctor(id));
	}

	@Override
	public Doctor getOneDoctor(Long id) {
		
		return repo.findById(id).orElseThrow(()->new DoctorNotFoundException("Record"+id+"Not Found"));
	}

	@Override
	public void updateDoctor(Doctor doc) {
		if(repo.existsById(doc.getId()))
			repo.save(doc);
		else
			throw new DoctorNotFoundException("Record"+doc.getId()+"Not Found");
	}

	@Override
	public Map<Long, String> getDoctorIdAndNames() {
		List<Object[]> list = repo.getDoctorIdAndNames();
		Map<Long,String> map = MyCollectionsUtil.convertToMapIndex(list);
		return map;
	}

	@Override
	public List<Doctor> findDoctorBySpecName(Long specId) {
		return repo.findDoctorBySpecName(specId);
	}
	
	@Override
	public long getDoctorCount() {
		return repo.count();
	}

}
