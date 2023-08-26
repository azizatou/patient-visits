package com.app.medical.patient.web.api.patient;

import com.app.medical.patient.adapter.port.in.PatientResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record CreatePatientReply(
        @Schema(description = "result of patient creation") @JsonProperty(CODE_FIELD) CreatePatientReplyCode code,
        @Schema(description = "errors that occurs during patient creation") @JsonProperty(ERRORS_FILED) List<String> errors) {

    public enum CreatePatientReplyCode {
        CREATED,
        NOT_CREATED
    }

    public static final String CODE_FIELD = "code";
    public static final String ERRORS_FILED = "errors";

    public static CreatePatientReply of(final PatientResult patientResult) {

        if (patientResult.succeeded()) {
            return new CreatePatientReply(CreatePatientReplyCode.CREATED, null);
        }
        return new CreatePatientReply(CreatePatientReplyCode.NOT_CREATED, List.of(patientResult.patientResultErrorCode().name()));
    }
}
