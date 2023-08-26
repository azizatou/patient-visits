package com.app.medical.patient.web;


import com.app.medical.patient.web.api.PatientVisitKey;
import com.app.medical.patient.web.api.CreateReply;
import com.app.medical.patient.web.api.UpdateReply;
import com.app.medical.patient.web.api.patientvisit.PatientVisitQuery;
import com.app.medical.patient.web.api.patientvisit.UpdatePatientVisitQuery;
import com.app.medical.patient.web.doc.PatientVisitApi;
import com.app.medical.patient.web.dto.PatientVisitDto;
import com.app.medical.patient.web.handler.PatientVisitHandler;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/appointment", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class PatientVisitController implements PatientVisitApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientVisitController.class);

    private final PatientVisitHandler patientVisitHandler;

    public PatientVisitController(final PatientVisitHandler patientVisitHandler) {
        this.patientVisitHandler = patientVisitHandler;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<CreateReply> createAppointment(@Valid @RequestBody final PatientVisitQuery createPatientVisitQuery) {
        LOGGER.debug("POST /_create_patient_visit?{}", createPatientVisitQuery);
        final CreateReply patientVisitReply = patientVisitHandler.handleCreatePatientVisit(createPatientVisitQuery);
        return ok(patientVisitReply);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<UpdateReply> updateAppointment(@Valid @RequestBody final UpdatePatientVisitQuery updatePatientVisitQuery) {
        LOGGER.debug("POST /_create_patient_visit?{}", updatePatientVisitQuery);
        final UpdateReply patientVisitReply = patientVisitHandler.handleUpdatePatientVisit(updatePatientVisitQuery);
        return ok(patientVisitReply);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PatientVisitDto> getAppointmentById(@PathVariable final long appointmentId) {
        LOGGER.debug("POST /_select_patient_visit_by_id?{}", appointmentId);
        return patientVisitHandler.handleGetPatientVisit(appointmentId)
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @PostMapping(value = "/select")
    public ResponseEntity<PatientVisitDto> getAppointmentByKey(@Valid @RequestBody final PatientVisitKey patientVisitQuery) {
        LOGGER.debug("POST /_select_patient_visit?{}", patientVisitQuery);
        return patientVisitHandler.handleGetPatientVisit(patientVisitQuery)
                .map(ResponseEntity::ok)
                .orElse(null);
    }

    @PostMapping(value = "/{securityNumber}")
    public ResponseEntity<List<PatientVisitDto>> getAppointmentsByPatient(@PathVariable final String securityNumber) {
        LOGGER.debug("POST /_select_all_patient_visit?{}", securityNumber);
        final List<PatientVisitDto> patientVisits = patientVisitHandler.handleGetPatientVisits(securityNumber);
        return ok(patientVisits);
    }
}
