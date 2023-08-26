package com.app.medical.patient.application;


import com.app.medical.patient.adapter.port.in.PatientResult;
import com.app.medical.patient.adapter.port.in.PatientUseCase;
import com.app.medical.patient.adapter.port.out.PatientPort;
import com.app.medical.patient.domain.Patient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.app.medical.patient.adapter.port.in.PatientResult.PatientResultErrorCode.ALREADY_EXISTS;
import static com.app.medical.patient.adapter.port.in.PatientResult.builder;
import static java.util.Objects.isNull;

@Service
class PatientUseCaseService implements PatientUseCase {

    private final PatientPort patientPort;

    PatientUseCaseService(final PatientPort patientPort) {
        this.patientPort = patientPort;
    }

    @Override
    public PatientResult save(final Patient patient) {
        if (isNull(patient)) {
            builder().withSucceeded(false);
        }

        final Optional<Patient> patientHolder = patientPort.getById(patient.socialSecurityNumber());
        if (patientHolder.isPresent()) {
            return builder()
                    .withSucceeded(false)
                    .withPatientResultErrorCode(ALREADY_EXISTS)
                    .build();
        }

        patientPort.save(patient);
        return builder().withSucceeded(true).build();
    }

    @Override
    public Optional<Patient> getById(final String socialSecurityNumber) {
        return patientPort.getById(socialSecurityNumber);
    }

    @Override
    public List<Patient> getAll(final int page, final int size) {
        return patientPort.findAll(page, size);
    }
}
