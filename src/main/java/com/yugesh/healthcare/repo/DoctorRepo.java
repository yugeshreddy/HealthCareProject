package com.yugesh.healthcare.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yugesh.healthcare.entity.Doctor;
@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {
	@Query("SELECT id, firstName, lastName FROM Doctor")
	public List<Object[]> getDoctorIdAndNames();
	
	@Query("SELECT doct FROM Doctor doct INNER JOIN doct.specialization as spc WHERE spc.id=:specId")
	public List<Doctor> findDoctorBySpecName(Long specId);
}
