package com.app.medical.patient.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record PatientDto(@JsonProperty(NAME_FIELD) String name,
                         @JsonProperty(SURNAME_FIELD) String surname,
                         @JsonProperty(BIRTH_DATE) LocalDate birthDate,
                         @JsonProperty(SECURITY_NUMBER_FIELD) String socialSecurityNumber
) {

    public static final String NAME_FIELD = "name";
    public static final String SURNAME_FIELD = "surname";
    public static final String BIRTH_DATE = "birthdate";
    public static final String SECURITY_NUMBER_FIELD = "social_security_number";

    public static PatientDtoBuilder builder() {
        return new PatientDtoBuilder();
    }

    public static class PatientDtoBuilder {
        private String name;
        private String surname;
        private LocalDate birthDate;
        private String socialSecurityNumber;

        private PatientDtoBuilder() {
        }

        public PatientDtoBuilder withName(final String name) {
            this.name = name;
            return this;
        }

        public PatientDtoBuilder withSurname(final String surname) {
            this.surname = surname;
            return this;
        }

        public PatientDtoBuilder withBirthDate(final LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public PatientDtoBuilder withSocialSecurityNumber(final String socialSecurityNumber) {
            this.socialSecurityNumber = socialSecurityNumber;
            return this;
        }

        public PatientDto build() {
            return new PatientDto(name, surname, birthDate, socialSecurityNumber);
        }
    }
}
