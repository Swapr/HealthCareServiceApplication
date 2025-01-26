package com.example.project.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.project.Model.Appointment;
import com.example.project.service.AppointmentService;

@RestController
public class AppointmentController {
	
	private AppointmentService appointmentService;
	
	public AppointmentController(AppointmentService appointmentService) {
		this.appointmentService=appointmentService;
	}
	
	@PostMapping("/appointment/register")
	public ResponseEntity<String>  registerAppointment(@RequestBody Appointment appointment){
		return appointmentService.registerAppointment(appointment);
	}
	
	@GetMapping("appointment/list")
	public ResponseEntity<List<Appointment>> getListOfAppointment(){
		return appointmentService.getListOfAppointment();
	}
	
	@GetMapping("/appointment/view/{appointmentld}")
	public ResponseEntity<Appointment> getAppointById(@PathVariable(value="appointmentld") String appointmentld){
		return appointmentService.getAppointmentById(appointmentld);
	}
	
	
	@DeleteMapping("/appointment/delete/{appointmentld}")
	public ResponseEntity<String> deleteAppoint(@PathVariable(value="appointmentld" ) String appointmentld){
		return appointmentService.deleteAppointmentById(appointmentld);
	}
	
	
	
	
	

}
