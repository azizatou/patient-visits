package com.app.medical.patient.application;

import com.app.medical.patient.adapter.port.in.PatientVisitResult.PatientVisitResultErrorCode;
import com.app.medical.patient.adapter.port.out.PatientPort;
import com.app.medical.patient.adapter.port.out.PatientVisitPort;
import com.app.medical.patient.domain.Patient;
import com.app.medical.patient.domain.PatientVisit;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.app.medical.patient.adapter.port.in.PatientVisitResult.PatientVisitResultErrorCode.*;

@Service
class ValidationService {

    final PatientVisitPort patientVisitPort;
    final PatientPort patientPort;

    ValidationService(final PatientVisitPort patientVisitPort, final PatientPort patientPort) {
        this.patientVisitPort = patientVisitPort;
        this.patientPort = patientPort;
    }

    public List<PatientVisitResultErrorCode> validateCreatePatientVisit(final PatientVisit patientVisit) {

        List<PatientVisitResultErrorCode> patientVisitResultErrorCodes = new ArrayList<>();

        final Optional<Patient> patientHolder = patientPort.getById(patientVisit.patientSocialSecurityNumber());
        if (patientHolder.isEmpty()) {
            patientVisitResultErrorCodes.add(PATIENT_NOT_FOUND);
        } else {
            final Optional<PatientVisit> exitingVisitHolder = patientVisitPort.getById(patientVisit.visitDateTime(), patientVisit.patientSocialSecurityNumber());
            if (exitingVisitHolder.isPresent()) {
                patientVisitResultErrorCodes.add(DUPLICATED_VISIT);
            }
        }
        return patientVisitResultErrorCodes;
    }


    public List<PatientVisitResultErrorCode> validateUpdatePatientVisit(final PatientVisit patientVisitToUpdate) {

        List<PatientVisitResultErrorCode> patientVisitResultErrorCodes = new ArrayList<>();

        final Optional<PatientVisit> exitingVisitHolder = patientVisitPort.getById(patientVisitToUpdate.visitId());
        if (exitingVisitHolder.isEmpty()) {
            patientVisitResultErrorCodes.add(VISIT_NOT_FOUND);
        }
        return patientVisitResultErrorCodes;
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


}
