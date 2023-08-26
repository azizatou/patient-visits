package com.app.medical.patient.adapter.port.in;

import com.app.medical.patient.domain.PatientVisit;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PatientVisitUseCase {
    PatientVisitResult createVisit(final PatientVisit patientVisit);

    PatientVisitResult updateVisit(final PatientVisit patientVisit);

    Optional<PatientVisit> getVisit(final LocalDateTime date, final String patientSocialSecurityNumber);

    Optional<PatientVisit> getVisitById(final long visitId);

    List<PatientVisit> getAllPatientVisits(final String socialSecurityNumber);
}
