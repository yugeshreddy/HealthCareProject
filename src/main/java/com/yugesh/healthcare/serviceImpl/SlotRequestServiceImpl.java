package com.yugesh.healthcare.serviceImpl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yugesh.healthcare.constants.SlotStatus;
import com.yugesh.healthcare.entity.SlotRequest;
import com.yugesh.healthcare.repo.SlotRequestRepository;
import com.yugesh.healthcare.service.SlotRequestService;


@Service
public class SlotRequestServiceImpl implements SlotRequestService {

	@Autowired
	private SlotRequestRepository repo;
	
	public Long saveSlotRequest(SlotRequest sr) {
		return repo.save(sr).getId();
	}
	
	public SlotRequest getOneSlotRequest(Long id) {
		Optional<SlotRequest> opt = repo.findById(id);
		if(opt!=null) {
			return opt.get();
		}
		return null;
	}

	public List<SlotRequest> getAllSlotRequests() {
		return repo.findAll();
	}

	@Transactional
	public void updateSlotRequestStatus(Long id, String status) {
		repo.updateSlotRequestStatus(id, status);
	}
	
	public List<SlotRequest> viewSlotsByPatientMail(String patientMail) {
		return repo.getAllPatientSlots(patientMail);
	}
	
	@Override
	public List<SlotRequest> viewSlotsByDoctorMail(String doctorMail) {
		return repo.getAllDoctorSlots(doctorMail,SlotStatus.ACCEPTED.name());
	}
	
	@Override
	public List<Object[]> getSlotsStatusAndCount() {
		return repo.getSlotsStatusAndCount();
	}

}
