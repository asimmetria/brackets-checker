package ru.altunin.bracketschecker.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Checking result")
public class BracketsCheckResponse {
    @Schema(description = "Result", example = "true")
    @JsonProperty("isCorrect")
    private boolean isCorrect;
}
