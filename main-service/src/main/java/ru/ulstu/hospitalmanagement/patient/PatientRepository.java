package ru.ulstu.hospitalmanagement.patient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    long countByStatus(PatientStatus status);
}
