package com.app.medical.patient.adapter.port.in;

import com.app.medical.patient.domain.Patient;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PatientUseCase {
    PatientResult save(final Patient patient);
    Optional<Patient> getById(final String securityNumber);
    List<Patient> getAll(int page, int size);
}
