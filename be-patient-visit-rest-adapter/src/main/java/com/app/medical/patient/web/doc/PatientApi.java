package com.app.medical.patient.web.doc;


import com.app.medical.patient.web.api.CreateReply;
import com.app.medical.patient.web.api.patient.CreatePatientQuery;
import com.app.medical.patient.web.dto.PatientDto;
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

@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateReply.class))),
        @ApiResponse(responseCode = "500", description = "Unexpected Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateReply.class),
                examples = {@ExampleObject(value = "{\"code\":\"UNEXPECTED_ERROR\"}")}))
})
@Tag(name = "Patient", description = "API Patient")
public interface PatientApi {

    @Operation(summary = "Patient creation endpoint", description = "This API handles patient creation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = CreateReply.class)
                    , examples = {
                    @ExampleObject(name = "if created", value = "{\"code\":\"CREATED\"}")
                    , @ExampleObject(name = "if not created", value = "{\"code\":\"NOT_CREATED\",\"errors\":[{\"codes\":[\"ALREADY_EXISTS\"]}}")
            }))
    })
    ResponseEntity<CreateReply> createPatient(@Valid @RequestBody final CreatePatientQuery createPatientQuery);


    @Operation(summary = "Get patient", description = "This API return the patient using his social security number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDto.class)))
    })
    ResponseEntity<PatientDto> getPatient(final @PathVariable String id);
}
