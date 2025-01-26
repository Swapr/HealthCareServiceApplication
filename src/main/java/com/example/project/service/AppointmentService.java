package com.example.project.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.project.Model.Appointment;
import com.example.project.repository.AppointmentRepository;

@Service
public class AppointmentService {
	
	private static final Logger LOGGER= LoggerFactory.getLogger(AppointmentService.class);


	private AppointmentRepository appointmentRepository;
	
	public AppointmentService(AppointmentRepository appointmentRepository) {
		this.appointmentRepository=appointmentRepository;
	}
	
	
	public ResponseEntity<String> registerAppointment(Appointment appointment){
		try {
			appointment.setBookingTime(new Date());                     
			appointment.setBooking_id(appointment.getPatientId());
			appointmentRepository.save(appointment);
			return ResponseEntity.status(HttpStatus.OK).body("Booking successful");
		} catch (Exception e) {
			LOGGER.debug("Booking failure"+e.getMessage()+" "+e.toString());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Booking failure"+e.getMessage());
		}
	}
	
	
	
	public ResponseEntity<List<Appointment>> getListOfAppointment(){
		try {
			List<Appointment> list= appointmentRepository.findAll();
			
			return ResponseEntity.status(HttpStatus.OK).body(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	
	public ResponseEntity<Appointment> getAppointmentById(String appointmentld){
		try {
			Optional<Appointment> optional= appointmentRepository.findById(appointmentld);
			if(optional.isPresent()) {
				Appointment appointment=optional.get();
				return ResponseEntity.status(HttpStatus.OK).body(appointment);	
			}else
				return ResponseEntity.status(HttpStatus.OK).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	
    public ResponseEntity<String> deleteAppointmentById(String appointmentId) {
    	
    	try {
			appointmentRepository.deleteById(appointmentId);
			return ResponseEntity.status(HttpStatus.OK).body("Appointment deleted");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("internal seerver error not deleted appointment");
		}
    }
    
    public void deleteAppointment(String appintId) {
    	appointmentRepository.deleteById(appintId);
    }
    
    public List<Appointment> getAllAppointments() {
    	List<Appointment> list=appointmentRepository.findAll();
    	return list;
    }

    
}
