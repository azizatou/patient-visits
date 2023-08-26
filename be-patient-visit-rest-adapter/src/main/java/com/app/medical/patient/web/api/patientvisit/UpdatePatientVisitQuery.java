package com.app.medical.patient.web.api.patientvisit;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdatePatientVisitQuery(
        @Schema(description = "Mandatory, the patient visit id") @NotBlank @JsonProperty(ID_FIELD) long visitId,
        @Valid @JsonUnwrapped @NotNull PatientVisitQuery patientVisitQuery
) {
    public static final String ID_FIELD = "id";
}
