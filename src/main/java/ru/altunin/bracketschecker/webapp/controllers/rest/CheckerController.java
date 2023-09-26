package ru.altunin.bracketschecker.webapp.controllers.rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.altunin.bracketschecker.models.dto.ErrorResponse;
import ru.altunin.bracketschecker.service.abstracts.BracketsChecker;
import ru.altunin.bracketschecker.models.dto.BracketsCheckResponse;
import ru.altunin.bracketschecker.models.dto.RequestText;

@RestController
@RequestMapping("/api")
@OpenAPIDefinition(info =
@Info(
        title = "Checking opening and closing characters",
        version = "0.0.1",
        description = "Checking the correct placement of opening and closing characters  in the text. <br />" +
                "With default settings, the API checks the following pairs of opening and closing characters: <br />" +
                "<b> ( : ) <br /> [ : ] <br /> { : } </b> <br /><br />" +
                "The following characters are considered useful:<br />" +
                "<b>[a-zA-Z0-9а-яА-Я]</b> <br /><br />" +
                "Other characters are not taken into account. <br /><br />" +
                "Ex: <br />" +
                "<b>(some) text == true <br />" +
                "{(st)st(st)} == true <br />" +
                ")some (text) == false <br />" +
                "(some [!] text) == false <br /></b>"
)
)
public class CheckerController {
    private final BracketsChecker bracketsChecker;

    public CheckerController(@Qualifier("firstImpl") BracketsChecker bracketsChecker) {
        this.bracketsChecker = bracketsChecker;
    }

    @Operation(summary = "Checking opening and closing characters",
            responses = {
                    @ApiResponse(description = "Checking completed ", responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = BracketsCheckResponse.class))),
                    @ApiResponse(
                            description = "Invalid input text!",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)
                            ))})
    @PostMapping("/checkBrackets")
    public ResponseEntity<BracketsCheckResponse> check(@Parameter(description = "The text that needs to be checked",
            schema = @Schema(implementation = RequestText.class))
                                                       @RequestBody RequestText request) {
        return ResponseEntity.ok(new BracketsCheckResponse(bracketsChecker.check(request.getText())));
    }

}
