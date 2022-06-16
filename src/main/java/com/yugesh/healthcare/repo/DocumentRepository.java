package com.yugesh.healthcare.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yugesh.healthcare.entity.Document;


public interface DocumentRepository 
	extends JpaRepository<Document, Long> 
{

	@Query("SELECT docId,docName FROM Document")
	List<Object[]> getDocumentIdAndName();

}
