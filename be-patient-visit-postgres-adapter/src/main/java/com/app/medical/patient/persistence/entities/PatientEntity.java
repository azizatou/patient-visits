package com.app.medical.patient.persistence.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "patient")
public class PatientEntity {

    private static final String ID_FIELD = "id";
    public static final String NAME_FIELD = "name";
    public static final String SURNAME_FIELD = "surname";
    public static final String BIRTH_DATE = "birthdate";
    public static final String SECURITY_NUMBER_FIELD = "social_security_number";

    public PatientEntity(final String name, final String surname, final LocalDate birthdate, final String securityNumber) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.securityNumber = securityNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_FIELD, nullable = false)
    private long patientId;

    @Column(name = NAME_FIELD, nullable = false)
    private String name;

    @Column(name = SURNAME_FIELD, nullable = false)
    private String surname;

    @Column(name = BIRTH_DATE, nullable = false)
    private LocalDate birthdate;

    @Column(name = SECURITY_NUMBER_FIELD, nullable = false)
    private String securityNumber;


    public long getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getSecurityNumber() {
        return securityNumber;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientEntity that = (PatientEntity) o;
        return patientId == that.patientId && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(birthdate, that.birthdate) && Objects.equals(securityNumber, that.securityNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, name, surname, birthdate, securityNumber);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PatientEntity.class.getSimpleName() + "[", "]")
                .add("patientId=" + patientId)
                .add("name='" + name + "'")
                .add("surname='" + surname + "'")
                .add("birthdate=" + birthdate)
                .add("securityNumber='" + securityNumber + "'")
                .toString();
    }

    public static PatientEntityBuilder builder() {
        return new PatientEntityBuilder();
    }

    public static class PatientEntityBuilder {
        private String name;
        private String surname;
        private LocalDate birthDate;
        private String socialSecurityNumber;

        private PatientEntityBuilder() {
        }

        public PatientEntityBuilder withName(final String name) {
            this.name = name;
            return this;
        }

        public PatientEntityBuilder withSurname(final String surname) {
            this.surname = surname;
            return this;
        }

        public PatientEntityBuilder withBirthDate(final LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public PatientEntityBuilder withSocialSecurityNumber(final String socialSecurityNumber) {
            this.socialSecurityNumber = socialSecurityNumber;
            return this;
        }

        public PatientEntity build() {
            return new PatientEntity(name, surname, birthDate, socialSecurityNumber);
        }
    }
}
