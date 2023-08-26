package com.app.medical.patient.web;


import com.app.medical.patient.web.api.CreateReply;
import com.app.medical.patient.web.api.patient.CreatePatientQuery;
import com.app.medical.patient.web.doc.PatientApi;
import com.app.medical.patient.web.dto.PatientDto;
import com.app.medical.patient.web.handler.PatientHandler;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/patient", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class PatientController implements PatientApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);

    private final PatientHandler patientHandler;

    public PatientController(final PatientHandler patientHandler) {
        this.patientHandler = patientHandler;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<CreateReply> createPatient(@Valid final CreatePatientQuery createPatientQuery) {
        LOGGER.debug("POST /_create_patient?{}", createPatientQuery);
        final CreateReply createPatientReply = patientHandler.handleCreatePatient(createPatientQuery);
        return ok(createPatientReply);
    }

    @GetMapping(value = "/{socialSecurityNumber}")
    public ResponseEntity<PatientDto> getPatient(@NotBlank @PathVariable final String socialSecurityNumber) {
        LOGGER.debug("POST /_get_patient?{}", socialSecurityNumber);
        return patientHandler.handleGetPatient(socialSecurityNumber)
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

}
