package com.app.medical.patient.web.api;

import com.app.medical.patient.adapter.port.in.PatientResult;
import com.app.medical.patient.adapter.port.in.PatientVisitResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record UpdateReply(
        @Schema(description = "result code") @JsonProperty(CODE_FIELD) ReplyCode code,
        @Schema(description = "errors that occurs during patient creation") @JsonProperty(ERRORS_FILED) List<String> errors) {

    public enum ReplyCode {
        UPDATED,
        NOT_UPDATED
    }

    public static final String CODE_FIELD = "code";
    public static final String ERRORS_FILED = "errors";

    public static UpdateReply of(final PatientResult patientResult) {
        if (patientResult.succeeded()) {
            return new UpdateReply(ReplyCode.UPDATED, null);
        }
        return new UpdateReply(ReplyCode.NOT_UPDATED, List.of(patientResult.patientResultErrorCode().name()));
    }

    public static UpdateReply of(final PatientVisitResult patientVisitResult) {
        if (patientVisitResult.succeeded()) {
            return new UpdateReply(ReplyCode.UPDATED, null);
        }
        return new UpdateReply(ReplyCode.NOT_UPDATED,
                patientVisitResult.patientVisitResultErrorCodes().stream()
                        .map(Enum::name)
                        .toList());
    }
}
