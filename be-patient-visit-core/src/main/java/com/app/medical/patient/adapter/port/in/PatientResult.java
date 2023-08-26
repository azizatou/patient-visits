package com.app.medical.patient.adapter.port.in;

public record PatientResult(boolean succeeded, PatientResultErrorCode patientResultErrorCode) {

    public enum PatientResultErrorCode {
        ALREADY_EXISTS
    }

    public static PatientResultBuilder builder() {
        return new PatientResultBuilder();
    }

    public static class PatientResultBuilder {
        private boolean succeeded;
        private PatientResultErrorCode patientResultErrorCode;

        private PatientResultBuilder() {
        }

        public PatientResult.PatientResultBuilder withSucceeded(final boolean succeeded) {
            this.succeeded = succeeded;
            return this;
        }

        public PatientResult.PatientResultBuilder withPatientResultErrorCode(
                final PatientResultErrorCode patientVisitErrorCode) {
            this.patientResultErrorCode = patientVisitErrorCode;
            return this;
        }

        public PatientResult build() {
            return new PatientResult(succeeded, patientResultErrorCode);
        }
    }
}