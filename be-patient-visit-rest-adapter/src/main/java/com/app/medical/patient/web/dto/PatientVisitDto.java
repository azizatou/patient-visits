package com.app.medical.patient.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record PatientVisitDto(
        @JsonProperty(VISIT_DATE_TIME_FIELD) LocalDateTime visitDateTime,
        @JsonProperty(SECURITY_NUMBER_FIELD) String socialSecurityNumber,
        @JsonProperty(FAMILY_HISTORIC_FIELD) String familyHistoric,
        @JsonProperty(VISIT_REASON_FIELD) String visitReason,
        @JsonProperty(VISIT_TYPE_FIELD) String visitType

) {

    public static final String VISIT_REASON_FIELD = "reason";
    public static final String VISIT_TYPE_FIELD = "type";
    public static final String VISIT_DATE_TIME_FIELD = "datetime";
    public static final String SECURITY_NUMBER_FIELD = "social_security_number";
    public static final String FAMILY_HISTORIC_FIELD = "historic";

    public static PatientVisitDtoBuilder builder() {
        return new PatientVisitDtoBuilder();
    }

    public static class PatientVisitDtoBuilder {
        private String familyHistoric;
        private String visitReason;
        private String visitType;
        private LocalDateTime visitDateTime;
        private String socialSecurityNumber;

        private PatientVisitDtoBuilder() {
        }

        public PatientVisitDtoBuilder withFamilyHistoric(final String familyHistoric) {
            this.familyHistoric = familyHistoric;
            return this;
        }

        public PatientVisitDtoBuilder withVisitReason(final String visitReason) {
            this.visitReason = visitReason;
            return this;
        }

        public PatientVisitDtoBuilder withVisitType(final String visitType) {
            this.visitType = visitType;
            return this;
        }

        public PatientVisitDtoBuilder withVisitDateTime(final LocalDateTime visitDateTime) {
            this.visitDateTime = visitDateTime;
            return this;
        }

        public PatientVisitDtoBuilder withSocialSecurityNumber(final String socialSecurityNumber) {
            this.socialSecurityNumber = socialSecurityNumber;
            return this;
        }

        public PatientVisitDto build() {
            return new PatientVisitDto(visitDateTime, socialSecurityNumber, familyHistoric, visitReason, visitType );
        }
    }
}
