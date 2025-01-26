package com.example.project.repository;

import com.example.project.Model.Patient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,String>{

}
