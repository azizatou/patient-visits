package com.app.medical.patient.web.api.patient;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

public record CreatePatientQuery(
        @Schema(description = "Not Mandatory, the name of the patient") @Email @JsonProperty(EMAIL_FIELD) String email,
        @Schema(description = "Mandatory, the name of the patient") @NotBlank @JsonProperty(NAME_FIELD) String name,
        @Schema(description = "Mandatory, the surname of the patient") @NotBlank @JsonProperty(SURNAME_FIELD) String surname,
        @Schema(description = "Mandatory, the birthdate of the patient") @NotBlank @DateTimeFormat(pattern = "YYYY-MM-DD") @JsonProperty(BIRTH_DATE) LocalDate birthDate,
        @Schema(description = "Mandatory, the social security number of the patient") @NotNull @Size(max=25, min=20) @JsonProperty(SECURITY_NUMBER_FIELD) String socialSecurityNumber
) {

    public static final String EMAIL_FIELD="email";
    public static final String NAME_FIELD = "name";
    public static final String SURNAME_FIELD = "surname";
    public static final String BIRTH_DATE = "birthdate";
    public static final String SECURITY_NUMBER_FIELD = "social_security_number";
}
