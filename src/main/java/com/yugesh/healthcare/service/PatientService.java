package com.yugesh.healthcare.service;

import java.util.List;

import com.yugesh.healthcare.entity.Patient;

public interface PatientService {
	Long savePatient(Patient patient);

	void updatePatient(Patient patient);

	void deletePatient(Long id);

	Patient getOnePatient(Long id);

	List<Patient> getAllPatients();

	Patient getOneByEmail(String email);

	long getPatientCount();
}
