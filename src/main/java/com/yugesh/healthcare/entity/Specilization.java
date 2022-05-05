package com.yugesh.healthcare.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="spec_tab")
public class Specilization {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="spec_id")
	private Long id;
	@Column(name="spec_name",length = 60)
	private String specName;
	@Column(name="spec_code",length = 10)
	private String specCode;
	@Column(name="spec_note",length = 50)
	private String specNote;

}
