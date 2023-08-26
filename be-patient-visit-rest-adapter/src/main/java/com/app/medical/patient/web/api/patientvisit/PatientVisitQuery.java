package com.app.medical.patient.web.api.patientvisit;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record PatientVisitQuery(
        @Schema(description = "Not Mandatory, the name of the patient") @Email @JsonProperty(FAMILY_HISTORIC_FIELD) String familyHistoric,
        @Schema(description = "Mandatory, visit reason of the patient") @NotBlank @JsonProperty(VISIT_REASON_FIELD) String visitReason,
        @Schema(description = "Mandatory, visit type of the patient") @NotBlank @JsonProperty(VISIT_TYPE_FIELD) String visitType,
        @Schema(description = "Mandatory, the visit datetime of the patient") @NotBlank @DateTimeFormat(pattern = "YYYY-MM-DD HH:MM") @JsonProperty(VISIT_DATE_TIME_FIELD) LocalDateTime visitDateTime,
        @Schema(description = "Mandatory, the social security number of the patient") @NotNull @Size(max = 25, min = 20) @JsonProperty(SECURITY_NUMBER_FIELD) String socialSecurityNumber
) {

    public static final String VISIT_REASON_FIELD = "reason";
    public static final String VISIT_TYPE_FIELD = "type";
    public static final String VISIT_DATE_TIME_FIELD = "datetime";
    public static final String SECURITY_NUMBER_FIELD = "social_security_number";
    public static final String FAMILY_HISTORIC_FIELD = "historic";
}
