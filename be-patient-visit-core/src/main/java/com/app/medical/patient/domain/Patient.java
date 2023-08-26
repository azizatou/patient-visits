package com.app.medical.patient.domain;

import java.time.LocalDate;

public record Patient(long patientId, String name, String surname, LocalDate birthDate, String socialSecurityNumber) {

    public static PatientBuilder builder() {
        return new PatientBuilder();
    }

    public static class PatientBuilder {
        private long patientId;
        private String name;
        private String surname;
        private LocalDate birthDate;
        private String socialSecurityNumber;

        private PatientBuilder() {
        }

        public PatientBuilder withPatientId(final long patientId) {
            this.patientId = patientId;
            return this;
        }

        public PatientBuilder withName(final String name) {
            this.name = name;
            return this;
        }

        public PatientBuilder withSurname(final String surname) {
            this.surname = surname;
            return this;
        }

        public PatientBuilder withBirthDate(final LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public PatientBuilder withSocialSecurityNumber(final String socialSecurityNumber) {
            this.socialSecurityNumber = socialSecurityNumber;
            return this;
        }

        public Patient build() {
            return new Patient(patientId, name, surname, birthDate, socialSecurityNumber);
        }
    }
}
