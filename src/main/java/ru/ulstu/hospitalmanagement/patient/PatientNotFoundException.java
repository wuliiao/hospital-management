package ru.ulstu.hospitalmanagement.patient;

public class PatientNotFoundException extends RuntimeException {

    public PatientNotFoundException(Long id) {
        super("Patient with id " + id + " not found");
    }
}
