package com.yugesh.healthcare.service;

import java.util.List;

import com.yugesh.healthcare.entity.Document;


public interface DocumentService {

	void saveDocument(Document doc);
	List<Object[]> getDocumentIdAndName();
	void deleteDocumentById(Long id);
	Document getDocumentById(Long id);
}
