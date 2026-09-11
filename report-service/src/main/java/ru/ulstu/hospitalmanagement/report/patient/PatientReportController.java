package ru.ulstu.hospitalmanagement.report.patient;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports/patients")
public class PatientReportController {

    private final PatientRepository patientRepository;

    public PatientReportController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping
    public List<Patient> findAllForReport() {
        return patientRepository.findAll();
    }
}
