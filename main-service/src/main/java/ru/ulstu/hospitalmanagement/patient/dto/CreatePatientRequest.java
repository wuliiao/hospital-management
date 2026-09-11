package ru.ulstu.hospitalmanagement.patient.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public record CreatePatientRequest(
        @NotBlank String fullName,
        @NotBlank String diagnosis,
        String treatment,
        LocalDate admissionDate
) {
}
