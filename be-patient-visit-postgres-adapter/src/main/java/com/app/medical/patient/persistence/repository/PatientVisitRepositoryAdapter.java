package com.app.medical.patient.persistence.repository;

import com.app.medical.patient.adapter.port.out.PatientVisitPort;
import com.app.medical.patient.domain.PatientVisit;
import com.app.medical.patient.persistence.entities.PatientEntity;
import com.app.medical.patient.persistence.entities.PatientVisitEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
class PatientVisitRepositoryAdapter implements PatientVisitPort {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientVisitRepositoryAdapter.class);

    private final PatientJPARepository patientJPARepository;
    private final PatientVisitJPARepository patientVisitJPARepository;

    PatientVisitRepositoryAdapter(final PatientJPARepository patientJPARepository, final PatientVisitJPARepository patientVisitJPARepository) {
        this.patientJPARepository = patientJPARepository;
        this.patientVisitJPARepository = patientVisitJPARepository;
    }

    @Override
    public void save(final PatientVisit patientVisit) {
        patientVisitJPARepository.save(toEntityPatientVisit(patientVisit));
    }

    @Override
    public void update(final PatientVisit patientVisitToUpdate) {
        patientVisitJPARepository.save(toEntityPatientVisit(patientVisitToUpdate));
    }

    @Override
    public Optional<PatientVisit> getById(final LocalDateTime dateTime, final String patientSocialSecurityNumber) {

        final Optional<PatientEntity> patientEntity = patientJPARepository
                .findBySocialSecurityNumber(patientSocialSecurityNumber);

        return patientEntity
                .map(entity -> patientVisitJPARepository.findByDatetimeAndPatientId(dateTime, entity.getPatientId())
                        .map(this::toDomainPatientVisit))
                .orElse(null);

    }

    @Override
    public Optional<PatientVisit> getById(final long patientVisitId) {

        return patientVisitJPARepository.findById(patientVisitId)
                .map(this::toDomainPatientVisit);

    }

    @Override
    public List<PatientVisit> getAllBySocialSecurityNumber(final String patientSocialSecurityNumber) {

        final Optional<PatientEntity> patientEntity = patientJPARepository
                .findBySocialSecurityNumber(patientSocialSecurityNumber);

        return patientEntity
                .map(entity -> patientVisitJPARepository.findAllByPatientId(entity.getPatientId()).stream()
                        .map(this::toDomainPatientVisit)
                        .toList())
                .orElseGet(List::of);

    }


    private PatientVisitEntity toEntityPatientVisit(final PatientVisit domainPatientVisit) {

        final Optional<PatientEntity> patientEntity = patientJPARepository
                .findBySocialSecurityNumber(domainPatientVisit.patientSocialSecurityNumber());

        return PatientVisitEntity.builder()
                .withVisitDateTime(domainPatientVisit.visitDateTime())
                .withVisitType(domainPatientVisit.visitType().name())
                .withVisitReason(domainPatientVisit.visitReason().name())
                .withPatientEntity(patientEntity.get())
                .build();
    }

    private PatientVisit toDomainPatientVisit(final PatientVisitEntity patientVisitEntity) {
        return PatientVisit.builder()
                .withVisitId(patientVisitEntity.getAppointmentId())
                .withPatientSocialSecurityNumber(patientVisitEntity.getPatientEntity().getSecurityNumber())
                .withVisitDateTime(patientVisitEntity.getAppointmentDatetime())
                .withVisitType(Enum.valueOf(PatientVisit.VisitType.class, patientVisitEntity.getAppointmentType()))
                .withVisitReason(Enum.valueOf(PatientVisit.VisitReason.class, patientVisitEntity.getAppointmentReason()))
                .withFamilyHistory(patientVisitEntity.getFamilyHistoric())
                .build();
    }
}
