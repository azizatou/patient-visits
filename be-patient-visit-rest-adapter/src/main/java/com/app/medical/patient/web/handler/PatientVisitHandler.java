package com.app.medical.patient.web.handler;

import com.app.medical.patient.adapter.port.in.PatientVisitResult;
import com.app.medical.patient.adapter.port.in.PatientVisitResult.PatientVisitResultErrorCode;
import com.app.medical.patient.adapter.port.in.PatientVisitUseCase;
import com.app.medical.patient.domain.PatientVisit;
import com.app.medical.patient.web.api.CreateReply;
import com.app.medical.patient.web.api.PatientVisitKey;
import com.app.medical.patient.web.api.UpdateReply;
import com.app.medical.patient.web.api.patientvisit.PatientVisitQuery;
import com.app.medical.patient.web.api.patientvisit.UpdatePatientVisitQuery;
import com.app.medical.patient.web.dto.PatientVisitDto;
import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

import static com.app.medical.patient.adapter.port.in.PatientVisitResult.PatientVisitResultErrorCode.UNKNOWN_VISIT_REASON;
import static com.app.medical.patient.adapter.port.in.PatientVisitResult.PatientVisitResultErrorCode.UNKNOWN_VISIT_TYPE;

public class PatientVisitHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientVisitHandler.class);

    private final PatientVisitUseCase patientVisitUseCase;

    public PatientVisitHandler(final PatientVisitUseCase patientVisitUseCase) {
        this.patientVisitUseCase = patientVisitUseCase;
    }


    public CreateReply handleCreatePatientVisit(final PatientVisitQuery createPatientVisitQuery) {
        final PatientVisit patientVisit = toPatientVisit(createPatientVisitQuery);
        final PatientVisitResult patientVisitResult = patientVisitUseCase.createVisit(patientVisit);
        return CreateReply.of(patientVisitResult);
    }


    public UpdateReply handleUpdatePatientVisit(final UpdatePatientVisitQuery updatePatientVisitQuery) {
        final PatientVisit patientVisitToUpdate = toPatientVisitWithId(updatePatientVisitQuery);
        final PatientVisitResult patientVisitResult = patientVisitUseCase.updateVisit(patientVisitToUpdate);
        return UpdateReply.of(patientVisitResult);
    }


    public Optional<PatientVisitDto> handleGetPatientVisit(final PatientVisitKey patientVisitQuery) {
        return patientVisitUseCase.getVisit(patientVisitQuery.visitDateTime(), patientVisitQuery.socialSecurityNumber())
                .map(PatientVisitHandler::toDtoPatientVisit);
    }

    public Optional<PatientVisitDto> handleGetPatientVisit(final long visitId) {
        return patientVisitUseCase.getVisitById(visitId)
                .map(PatientVisitHandler::toDtoPatientVisit);

    }

    public List<PatientVisitDto> handleGetPatientVisits(final String socialSecurityNumber) {
        return patientVisitUseCase.getAllPatientVisits(socialSecurityNumber).stream()
                .map(PatientVisitHandler::toDtoPatientVisit)
                .toList();
    }


    private PatientVisit toPatientVisit(final PatientVisitQuery createPatientVisitQuery) {

        return PatientVisit.builder()
                .withVisitDateTime(createPatientVisitQuery.visitDateTime())
                .withPatientSocialSecurityNumber(createPatientVisitQuery.socialSecurityNumber())
                .withVisitType(Enum.valueOf(PatientVisit.VisitType.class, createPatientVisitQuery.visitType()))
                .withVisitReason(Enum.valueOf(PatientVisit.VisitReason.class, createPatientVisitQuery.visitReason()))
                .withFamilyHistory(createPatientVisitQuery.familyHistoric())
                .build();

    }

    private PatientVisit toPatientVisitWithId(final UpdatePatientVisitQuery updatePatientVisitQuery) {

        final PatientVisitQuery patientVisitQuery = updatePatientVisitQuery.patientVisitQuery();

        return PatientVisit.builder()
                .withVisitId(updatePatientVisitQuery.visitId())
                .withVisitDateTime(patientVisitQuery.visitDateTime())
                .withPatientSocialSecurityNumber(patientVisitQuery.socialSecurityNumber())
                .withVisitType(Enum.valueOf(PatientVisit.VisitType.class, patientVisitQuery.visitType()))
                .withVisitReason(Enum.valueOf(PatientVisit.VisitReason.class, patientVisitQuery.visitReason()))
                .withFamilyHistory(patientVisitQuery.familyHistoric())
                .build();

    }

    private static void validateEnumerations(final String visitType, final String visitReason,
                                             final List<PatientVisitResultErrorCode> patientVisitResultErrorCodes) {
        if (!EnumUtils.isValidEnum(PatientVisit.VisitType.class, visitType)) {
            patientVisitResultErrorCodes.add(UNKNOWN_VISIT_TYPE);
        }

        if (!EnumUtils.isValidEnum(PatientVisit.VisitReason.class, visitReason)) {
            patientVisitResultErrorCodes.add(UNKNOWN_VISIT_REASON);
        }
    }

    private static PatientVisitDto toDtoPatientVisit(final PatientVisit patientVisit) {
        return PatientVisitDto.builder()
                .withVisitDateTime(patientVisit.visitDateTime())
                .withSocialSecurityNumber(patientVisit.patientSocialSecurityNumber())
                .withVisitReason(patientVisit.visitReason().name())
                .withVisitType(patientVisit.visitType().name())
                .withFamilyHistoric(patientVisit.familyHistory())
                .build();
    }

}
