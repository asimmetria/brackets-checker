package ru.altunin.bracketschecker.models.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Text for checking")
public class RequestText {
    @Schema(description = "Text", example = "Lorem (ipsum) dolor {sit amet}, consectetur [adipiscing] elit.")
    private String text;
}
