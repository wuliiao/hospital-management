package ru.ulstu.hospitalmanagement.patient;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.ulstu.hospitalmanagement.patient.dto.CreatePatientRequest;
import ru.ulstu.hospitalmanagement.patient.dto.PatientReportResponse;
import ru.ulstu.hospitalmanagement.patient.dto.TreatmentRequest;
import ru.ulstu.hospitalmanagement.patient.dto.UpdatePatientRequest;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> findAll() {
        return patientService.findAll();
    }

    @GetMapping("/{id}")
    public Patient findById(@PathVariable Long id) {
        return patientService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Patient create(@Valid @RequestBody CreatePatientRequest request) {
        return patientService.create(request);
    }

    @PutMapping("/{id}")
    public Patient update(@PathVariable Long id, @Valid @RequestBody UpdatePatientRequest request) {
        return patientService.update(id, request);
    }

    @PatchMapping("/{id}/treatment")
    public Patient assignTreatment(@PathVariable Long id, @Valid @RequestBody TreatmentRequest request) {
        return patientService.assignTreatment(id, request.treatment());
    }

    @PatchMapping("/{id}/discharge")
    public Patient discharge(@PathVariable Long id) {
        return patientService.discharge(id);
    }

    @GetMapping("/report")
    public PatientReportResponse report() {
        return patientService.report();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        patientService.delete(id);
    }
}
