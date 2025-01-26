package com.example.project.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.Model.Patient;
import com.example.project.service.PatientService;

@RestController
public class PatientController {
	
private PatientService patientService;
	
	@Autowired
	public PatientController(PatientService patientService) {
		this.patientService=patientService;
	}
	
	@PostMapping("/patients/register")
	public ResponseEntity<String> registerPatient(@RequestBody Patient patient){
		return patientService.registerPatient(patient);
	}
	
	
	@GetMapping("patients/list")
	public ResponseEntity<List<Patient>> getListOfPatients(){
		return patientService.getListOfPatients();
	}
	
	@GetMapping("patients/view/{patientId}")
	public ResponseEntity<Patient> getPatientById(@PathVariable(value = "patientId") String patientId){
		return patientService.getPatientById(patientId);
		
	}
	
	
	@DeleteMapping("patients/delete/{PatientId}")
	public ResponseEntity<String> deletePatinet(@PathVariable(value = "PatientId") String PatientId){
		return patientService.deletePatient(PatientId);
	}



}
