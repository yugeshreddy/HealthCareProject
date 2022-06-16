package com.yugesh.healthcare.service;

import java.util.List;

import com.yugesh.healthcare.entity.Appointment;


/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
public interface AppointmentService {
	Long saveAppointment(Appointment appointment);
	void updateAppointment(Appointment appointment);
	void deleteAppointment(Long id);
	Appointment getOneAppointment(Long id);
	List<Appointment> getAllAppointments();
	List<Object[]> getAppoinmentsByDoctor(Long docId);
	List<Object[]> getAppoinmentsByDoctorEmail(String userName);
	void updateSlotCountForAppoinment(Long id,int count);
	long getAppointmentCount();
}
