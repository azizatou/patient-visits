package com.app.medical.patient.web.doc;


import com.app.medical.patient.web.api.CreateReply;
import com.app.medical.patient.web.api.PatientVisitKey;
import com.app.medical.patient.web.api.UpdateReply;
import com.app.medical.patient.web.api.patientvisit.PatientVisitQuery;
import com.app.medical.patient.web.api.patientvisit.UpdatePatientVisitQuery;
import com.app.medical.patient.web.dto.PatientVisitDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateReply.class))),
        @ApiResponse(responseCode = "500", description = "Unexpected Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateReply.class),
                examples = {@ExampleObject(value = "{\"code\":\"UNEXPECTED_ERROR\"}")}))
})
@Tag(name = "Patient appointment", description = "API Patient")
public interface PatientVisitApi {

    @Operation(summary = "Patient appointment creation endpoint", description = "This API handles patient appointment creation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = CreateReply.class)
                    , examples = {
                    @ExampleObject(name = "if created", value = "{\"code\":\"CREATED\"}")
                    , @ExampleObject(name = "if not created", value = "{\"code\":\"NOT_CREATED\",\"errors\":[{\"codes\":[\"ALREADY_EXISTS\"]}}")
            }))
    })
    ResponseEntity<CreateReply> createAppointment(@Valid @RequestBody final PatientVisitQuery patientQuery);


    @Operation(summary = "Patient appointment update endpoint", description = "This API handles patient appointment update")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UpdateReply.class)
                    , examples = {
                    @ExampleObject(name = "if created", value = "{\"code\":\"UPDATED\"}")
                    , @ExampleObject(name = "if not created", value = "{\"code\":\"NOT_UPDATED\",\"errors\":[{\"codes\":[\"ALREADY_EXISTS\"]}}")
            }))
    })
    ResponseEntity<UpdateReply> updateAppointment(@Valid @RequestBody final UpdatePatientVisitQuery updatePatientQuery);


    @Operation(summary = "Get patient appointment by id endpoint", description = "This API return the patient appointment by identifier (database)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientVisitDto.class)))
    })
    ResponseEntity<PatientVisitDto> getAppointmentById(final @PathVariable long id);


    @Operation(summary = "Get Patient appointment by key (appointment datetime and patient security number endpoint)", description = "This API handles patient appointment retrieve")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PatientVisitDto.class)))
    })
    ResponseEntity<PatientVisitDto> getAppointmentByKey(@Valid @RequestBody final PatientVisitKey patientVisitKey);


    @Operation(summary = "Get all Patient's appointments by security number endpoint", description = "This API handles patient appointments retrieve ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PatientVisitDto.class)))
    })
    ResponseEntity<List<PatientVisitDto>> getAppointmentsByPatient(@PathVariable final String patientSecurityNumber);
}
