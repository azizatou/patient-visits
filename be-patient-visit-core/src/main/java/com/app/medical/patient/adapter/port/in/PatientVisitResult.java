package com.app.medical.patient.adapter.port.in;

import java.util.List;

public record PatientVisitResult(boolean succeeded, List<PatientVisitResultErrorCode> patientVisitResultErrorCodes) {

    public enum PatientVisitResultErrorCode {
        DUPLICATED_VISIT,
        PATIENT_NOT_FOUND,
        UNKNOWN_VISIT_TYPE,
        UNKNOWN_VISIT_REASON,
        VISIT_NOT_FOUND
    }

    public static PatientVisitResultBuilder builder() {
        return new PatientVisitResultBuilder();
    }

    public static class PatientVisitResultBuilder {
        private boolean succeeded;
        private List<PatientVisitResult.PatientVisitResultErrorCode> patientVisitResultErrorCodes;

        private PatientVisitResultBuilder() {
        }

        public PatientVisitResultBuilder withResult(final boolean result) {
            this.succeeded = result;
            return this;
        }

        public PatientVisitResultBuilder withPatientVisitResultErrorCodes(
                final List<PatientVisitResult.PatientVisitResultErrorCode> patientVisitResultErrorCodes) {
            this.patientVisitResultErrorCodes = patientVisitResultErrorCodes;
            return this;
        }

        public PatientVisitResult build() {
            return new PatientVisitResult(succeeded, patientVisitResultErrorCodes);
        }
    }
}
