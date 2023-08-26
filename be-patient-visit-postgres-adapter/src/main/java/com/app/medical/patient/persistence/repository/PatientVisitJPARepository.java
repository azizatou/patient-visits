package com.app.medical.patient.persistence.repository;

import com.app.medical.patient.persistence.entities.PatientVisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PatientVisitJPARepository extends JpaRepository<PatientVisitEntity, Long> {

    List<PatientVisitEntity> findAllByPatientId(long patientId);

    Optional<PatientVisitEntity> findByDatetimeAndPatientId(final LocalDateTime datetime, long patientId);
}
