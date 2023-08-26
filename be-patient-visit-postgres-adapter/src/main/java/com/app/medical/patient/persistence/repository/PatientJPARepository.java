package com.app.medical.patient.persistence.repository;

import com.app.medical.patient.persistence.entities.PatientEntity;
import com.app.medical.patient.persistence.entities.PatientVisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientJPARepository extends JpaRepository<PatientEntity, Long > {

    Optional<PatientEntity> findBySocialSecurityNumber(String socialSecurityNumber);
}
