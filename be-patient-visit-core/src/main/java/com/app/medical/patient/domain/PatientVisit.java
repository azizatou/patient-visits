package com.app.medical.patient.domain;

import java.time.LocalDateTime;

public record PatientVisit(
        long visitId,
        LocalDateTime visitDateTime,
        String patientSocialSecurityNumber,
        VisitType visitType,
        VisitReason visitReason,
        String familyHistory) {

    public enum VisitReason {
        FIRST,
        RECURRING,
        URGENT
    }

    public enum VisitType {
        HOME,
        OFFICE
    }

    public static PatientVisitBuilder builder() {
        return new PatientVisitBuilder();
    }

    public static class PatientVisitBuilder {
        private long visitId;
        private LocalDateTime visitDateTime;
        private String patientSocialSecurityNumber;
        private PatientVisit.VisitType visitType;
        private PatientVisit.VisitReason visitReason;
        private String familyHistory;

        private PatientVisitBuilder() {
        }

        public PatientVisitBuilder withVisitId(final long visitId) {
            this.visitId = visitId;
            return this;
        }

        public PatientVisitBuilder withVisitDateTime(final LocalDateTime visitDateTime) {
            this.visitDateTime = visitDateTime;
            return this;
        }

        public PatientVisitBuilder withPatientSocialSecurityNumber(final String patientSocialSecurityNumber) {
            this.patientSocialSecurityNumber = patientSocialSecurityNumber;
            return this;
        }

        public PatientVisitBuilder withVisitType(final PatientVisit.VisitType visitType) {
            this.visitType = visitType;
            return this;
        }

        public PatientVisitBuilder withVisitReason(final PatientVisit.VisitReason visitReason) {
            this.visitReason = visitReason;
            return this;
        }

        public PatientVisitBuilder withFamilyHistory(final String familyHistory) {
            this.familyHistory = familyHistory;
            return this;
        }

        public PatientVisit build() {
            return new PatientVisit(visitId, visitDateTime, patientSocialSecurityNumber, visitType, visitReason, familyHistory);
        }
    }

}
