package com.app.medical.patient.persistence.repository;

import com.app.medical.patient.adapter.port.out.PatientPort;
import com.app.medical.patient.domain.Patient;
import com.app.medical.patient.persistence.entities.PatientEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class PatientRepositoryAdapter implements PatientPort {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientRepositoryAdapter.class);

    private final PatientJPARepository jpaRepositoryAdapter;

    PatientRepositoryAdapter(final PatientJPARepository jpaRepositoryAdapter) {
        this.jpaRepositoryAdapter = jpaRepositoryAdapter;
    }

    @Override
    public void save(final Patient patient) {
        LOGGER.debug("Saving patient {},", patient);
        jpaRepositoryAdapter.save(toEntityPatient(patient));
    }

    @Override
    public Optional<Patient> getById(final String securityNumber) {
        return jpaRepositoryAdapter.findPatientBySocialSecurityNumber(securityNumber)
                .map(this::toDomainPatient);
    }

    @Override
    public List<Patient> findAll(final int page, final int size) {
        return jpaRepositoryAdapter.findAll(PageRequest.of(page, size))
                .map(this::toDomainPatient)
                .toList();
    }

    private PatientEntity toEntityPatient(final Patient domainPatient) {
        return PatientEntity.builder()
                .withName(domainPatient.name())
                .withSurname(domainPatient.surname())
                .withBirthDate(domainPatient.birthDate())
                .withSocialSecurityNumber(domainPatient.socialSecurityNumber())
                .build();
    }

    private Patient toDomainPatient(final PatientEntity patientEntity) {
        return Patient.builder()
                .withPatientId(patientEntity.getPatientId())
                .withName(patientEntity.getName())
                .withSurname(patientEntity.getSurname())
                .withBirthDate(patientEntity.getBirthdate())
                .withSocialSecurityNumber(patientEntity.getSecurityNumber())
                .build();
    }
}
