package com.app.medical.patient.web.handler;

import com.app.medical.patient.adapter.port.in.PatientResult;
import com.app.medical.patient.adapter.port.in.PatientUseCase;
import com.app.medical.patient.domain.Patient;
import com.app.medical.patient.web.api.CreateReply;
import com.app.medical.patient.web.api.patient.CreatePatientQuery;
import com.app.medical.patient.web.dto.PatientDto;

import java.util.Optional;

public class PatientHandler {

    private final PatientUseCase patientUseCase;

    public PatientHandler(final PatientUseCase patientUseCase) {
        this.patientUseCase = patientUseCase;
    }

    public CreateReply handleCreatePatient(final CreatePatientQuery createPatientQuery) {
        final PatientResult patientResult = patientUseCase.save(toDomainPatient(createPatientQuery));
        return CreateReply.of(patientResult);
    }


    public Optional<PatientDto> handleGetPatient(final String socialSecurityNumber) {
        return patientUseCase.getById(socialSecurityNumber)
                .map(PatientHandler::toDtoPatient);

    }

    private static Patient toDomainPatient(final CreatePatientQuery createPatientQuery) {
        return Patient.builder()
                .withName(createPatientQuery.name())
                .withSurname(createPatientQuery.surname())
                .withSocialSecurityNumber(createPatientQuery.socialSecurityNumber())
                .withBirthDate(createPatientQuery.birthDate())
                .build();
    }

    private static PatientDto toDtoPatient(final Patient patient) {
        return PatientDto.builder()
                .withName(patient.name())
                .withSurname(patient.surname())
                .withSocialSecurityNumber(patient.socialSecurityNumber())
                .withBirthDate(patient.birthDate())
                .build();
    }
}
