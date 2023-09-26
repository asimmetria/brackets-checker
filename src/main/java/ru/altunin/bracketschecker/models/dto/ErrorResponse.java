package ru.altunin.bracketschecker.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Error")
public class ErrorResponse {
    @Schema(description = "Code", example = "400")
    private int code;
    @Schema(description = "Error message", example = "The text is empty!")
    private String message;
}
