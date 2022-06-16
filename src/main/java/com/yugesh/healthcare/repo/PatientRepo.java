package com.yugesh.healthcare.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yugesh.healthcare.entity.Patient;

public interface PatientRepo extends JpaRepository<Patient, Long> {
	Optional<Patient> findByEmail(String email);

}
