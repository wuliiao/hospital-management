package ru.ulstu.hospitalmanagement.patient.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import ru.ulstu.hospitalmanagement.patient.PatientStatus;

public record UpdatePatientRequest(
        @NotBlank String fullName,
        @NotBlank String diagnosis,
        String treatment,
        PatientStatus status,
        LocalDate admissionDate,
        LocalDate dischargeDate
) {
}
