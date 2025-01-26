package com.example.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.project.Model.Patient;
import com.example.project.repository.PatientRepository;

@Service
public class PatientService {
	
	private PatientRepository patientRepository;
	
	@Autowired
	public PatientService(PatientRepository patientRepository) {
		this.patientRepository=patientRepository;
	}
	
	public ResponseEntity<String> registerPatient(Patient patient){
		try {
			patient.setPatient_Id(patient.getPatient_name());
			patientRepository.save(patient);
			return ResponseEntity.status(HttpStatus.OK).body("Registration successful");
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failure");
		}
	}
	
	public ResponseEntity<List<Patient>> getListOfPatients(){
		try {
			List<Patient> list= patientRepository.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(list);
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	public ResponseEntity<Patient> getPatientById(String patientId){
		try {
			Optional<Patient> optional= patientRepository.findById(patientId);
			if(optional.isPresent()) {
				Patient patient=optional.get();
				return ResponseEntity.status(HttpStatus.OK).body(patient);
			}
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	public ResponseEntity<String> deletePatient(String PatientId){
		try {
		     patientRepository.deleteById(PatientId);
		     return ResponseEntity.status(HttpStatus.OK).body("Patient deleted from databse");  
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("something wrong patient not deleted form databse"); 
		}
	}


}
