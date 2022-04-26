package com.yugesh.healthcare.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yugesh.healthcare.entity.Specilization;

public interface SpecilizationRepo extends JpaRepository<Specilization, Long> {

}
