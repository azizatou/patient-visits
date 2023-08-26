package com.app.medical.patient.web.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record PatientVisitKey(
        @Schema(description = "Mandatory, the visit datetime of the patient") @NotBlank @DateTimeFormat(pattern = "YYYY-MM-DD HH:MM") @JsonProperty(VISIT_DATE_TIME) LocalDateTime visitDateTime,
        @Schema(description = "Mandatory, the social security number of the patient") @NotNull @Size(max = 25, min = 20) @JsonProperty(SECURITY_NUMBER_FIELD) String socialSecurityNumber

) {
    public static final String VISIT_DATE_TIME = "datetime";
    public static final String SECURITY_NUMBER_FIELD = "social_security_number";
}
