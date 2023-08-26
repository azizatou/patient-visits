package com.app.medical.patient.adapter.port.out;

import com.app.medical.patient.domain.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientPort {
    void save(final Patient patient);
    Optional<Patient> getById(final String securityNumber);
    List<Patient> findAll(int page, int size);
}
