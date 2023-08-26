package com.app.medical.patient.application;

import com.app.medical.patient.adapter.port.in.PatientVisitResult;
import com.app.medical.patient.adapter.port.in.PatientVisitResult.PatientVisitResultErrorCode;
import com.app.medical.patient.adapter.port.in.PatientVisitUseCase;
import com.app.medical.patient.adapter.port.out.PatientVisitPort;
import com.app.medical.patient.domain.PatientVisit;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.app.medical.patient.adapter.port.in.PatientVisitResult.PatientVisitResultErrorCode.VISIT_NOT_FOUND;
import static com.app.medical.patient.adapter.port.in.PatientVisitResult.builder;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Service
class PatientVisitUseCaseService implements PatientVisitUseCase {

    private final PatientVisitPort patientVisitPort;
    private final ValidationService validationService;

    PatientVisitUseCaseService(final PatientVisitPort patientVisitPort, final ValidationService validationService) {
        this.patientVisitPort = patientVisitPort;
        this.validationService = validationService;

    }

    @Override
    public PatientVisitResult createVisit(final PatientVisit patientVisit) {

        final List<PatientVisitResultErrorCode> errorCodes = validationService.validateCreatePatientVisit(
                patientVisit);

        if (isEmpty(errorCodes)) {
            patientVisitPort.save(patientVisit);
            return builder().withResult(true).build();
        }

        return builder()
                .withResult(false)
                .withPatientVisitResultErrorCodes(errorCodes)
                .build();
    }

    @Override
    public PatientVisitResult updateVisit(final PatientVisit patientVisitToUpdate) {

        final List<PatientVisitResultErrorCode> errorCodes = validationService.validateUpdatePatientVisit(
                patientVisitToUpdate);

        if (errorCodes.isEmpty()) {
            patientVisitPort.update(patientVisitToUpdate);
            return builder().withResult(true).build();

        } else {
            return builder()
                    .withResult(false)
                    .withPatientVisitResultErrorCodes(List.of(VISIT_NOT_FOUND)).build();
        }

    }

    @Override
    public Optional<PatientVisit> getVisit(final LocalDateTime visitDateTime, final String patientSocialSecurityNumber) {
        return patientVisitPort.getById(visitDateTime, patientSocialSecurityNumber);
    }

    @Override
    public Optional<PatientVisit> getVisitById(final long visitId) {
        return patientVisitPort.getById(visitId);
    }

    @Override
    public List<PatientVisit> getAllPatientVisits(final String patientSocialSecurityNumber) {
        return patientVisitPort.getAllBySocialSecurityNumber(patientSocialSecurityNumber);
    }
}
