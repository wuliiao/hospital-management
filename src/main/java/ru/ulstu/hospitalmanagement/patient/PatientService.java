package ru.ulstu.hospitalmanagement.patient;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ulstu.hospitalmanagement.patient.dto.CreatePatientRequest;
import ru.ulstu.hospitalmanagement.patient.dto.PatientReportResponse;
import ru.ulstu.hospitalmanagement.patient.dto.UpdatePatientRequest;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient findById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));
    }

    @Transactional
    public Patient create(CreatePatientRequest request) {
        Patient patient = new Patient();
        patient.setFullName(request.fullName());
        patient.setDiagnosis(request.diagnosis());
        patient.setTreatment(request.treatment());
        patient.setAdmissionDate(request.admissionDate() == null ? LocalDate.now() : request.admissionDate());
        patient.setStatus(PatientStatus.IN_TREATMENT);
        return patientRepository.save(patient);
    }

    @Transactional
    public Patient update(Long id, UpdatePatientRequest request) {
        Patient patient = findById(id);
        patient.setFullName(request.fullName());
        patient.setDiagnosis(request.diagnosis());
        patient.setTreatment(request.treatment());
        patient.setStatus(request.status() == null ? patient.getStatus() : request.status());
        patient.setAdmissionDate(request.admissionDate() == null ? patient.getAdmissionDate() : request.admissionDate());
        patient.setDischargeDate(request.dischargeDate());
        return patient;
    }

    @Transactional
    public Patient assignTreatment(Long id, String treatment) {
        Patient patient = findById(id);
        patient.setTreatment(treatment);
        patient.setStatus(PatientStatus.IN_TREATMENT);
        patient.setDischargeDate(null);
        return patient;
    }

    @Transactional
    public Patient discharge(Long id) {
        Patient patient = findById(id);
        patient.setStatus(PatientStatus.DISCHARGED);
        patient.setDischargeDate(LocalDate.now());
        return patient;
    }

    public void delete(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new PatientNotFoundException(id);
        }
        patientRepository.deleteById(id);
    }

    public PatientReportResponse report() {
        return new PatientReportResponse(
                patientRepository.countByStatus(PatientStatus.IN_TREATMENT),
                patientRepository.countByStatus(PatientStatus.DISCHARGED)
        );
    }
}
