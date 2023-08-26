package com.app.medical.patient.adapter.port.out;

import com.app.medical.patient.domain.PatientVisit;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PatientVisitPort {

    void save(final PatientVisit patientVisit);

    void update(final PatientVisit patientVisitToUpdate);

    Optional<PatientVisit> getById(final LocalDateTime dateTime, final String patientSocialSecurityNumber);

    Optional<PatientVisit> getById(final long patientVisitId);

    List<PatientVisit> getAllBySocialSecurityNumber(final String patientSocialSecurityNumber);
}
