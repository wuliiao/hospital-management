package ru.ulstu.hospitalmanagement.patient.dto;

import jakarta.validation.constraints.NotBlank;

public record TreatmentRequest(@NotBlank String treatment) {
}
