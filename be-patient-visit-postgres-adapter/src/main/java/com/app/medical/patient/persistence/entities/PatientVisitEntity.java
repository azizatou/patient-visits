package com.app.medical.patient.persistence.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "appointment")
public class PatientVisitEntity {

    private static final String ID_FIELD = "id";
    private static final String VISIT_REASON_FIELD = "reason";
    private static final String VISIT_TYPE_FIELD = "type";
    private static final String VISIT_DATE_TIME_FIELD = "datetime";
    private static final String FAMILY_HISTORIC_FIELD = "family_historic";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_FIELD, nullable = false)
    private long appointmentId;

    @Column(name = VISIT_DATE_TIME_FIELD, nullable = false)
    private LocalDateTime appointmentDatetime;

    @ManyToOne(targetEntity = PatientEntity.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId", nullable = false)
    private PatientEntity patientEntity;

    @Column(name = VISIT_TYPE_FIELD, nullable = false)
    private String appointmentType;


    @Column(name = VISIT_REASON_FIELD, nullable = false)
    private String appointmentReason;

    @Column(name = FAMILY_HISTORIC_FIELD)
    private String familyHistoric;


    public PatientVisitEntity(
            final LocalDateTime appointmentDatetime,
            final PatientEntity patientEntity,
            final String appointmentType,
            final String appointmentReason,
            final String familyHistoric) {
        this.appointmentDatetime = appointmentDatetime;
        this.patientEntity = patientEntity;
        this.appointmentType = appointmentType;
        this.appointmentReason = appointmentReason;
        this.familyHistoric = familyHistoric;
    }

    public long getAppointmentId() {
        return appointmentId;
    }

    public LocalDateTime getAppointmentDatetime() {
        return appointmentDatetime;
    }

    public PatientEntity getPatientEntity() {
        return patientEntity;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public String getAppointmentReason() {
        return appointmentReason;
    }

    public String getFamilyHistoric() {
        return familyHistoric;
    }

    public static PatientVisitEntityBuilder builder() {
        return new PatientVisitEntityBuilder();
    }

    public static class PatientVisitEntityBuilder {
        private String familyHistoric;
        private String visitReason;
        private String visitType;
        private LocalDateTime visitDateTime;
        private PatientEntity patientEntity;

        private PatientVisitEntityBuilder() {
        }

        public PatientVisitEntityBuilder withFamilyHistoric(final String familyHistoric) {
            this.familyHistoric = familyHistoric;
            return this;
        }

        public PatientVisitEntityBuilder withVisitReason(final String visitReason) {
            this.visitReason = visitReason;
            return this;
        }

        public PatientVisitEntityBuilder withVisitType(final String visitType) {
            this.visitType = visitType;
            return this;
        }

        public PatientVisitEntityBuilder withVisitDateTime(final LocalDateTime visitDateTime) {
            this.visitDateTime = visitDateTime;
            return this;
        }


        public PatientVisitEntityBuilder withPatientEntity(final PatientEntity patientEntity) {
            this.patientEntity = patientEntity;
            return this;
        }

        public PatientVisitEntity build() {
            return new PatientVisitEntity(visitDateTime, patientEntity, visitType, visitReason, familyHistoric);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientVisitEntity that = (PatientVisitEntity) o;
        return appointmentId == that.appointmentId &&
                Objects.equals(appointmentDatetime, that.appointmentDatetime)
                && Objects.equals(patientEntity, that.patientEntity)
                && Objects.equals(appointmentType, that.appointmentType)
                && Objects.equals(appointmentReason, that.appointmentReason)
                && Objects.equals(familyHistoric, that.familyHistoric);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentId, appointmentDatetime, patientEntity, appointmentType, appointmentReason, familyHistoric);
    }
}
