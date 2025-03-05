package de.fi.webapp.presentation.controller.v1;


import de.fi.webapp.presentation.dto.PersonDto;
import de.fi.webapp.presentation.dto.SchweinDto;
import de.fi.webapp.presentation.mapper.PersonDtoMapper;
import de.fi.webapp.presentation.mapper.SchweinDtoMapper;
import de.fi.webapp.service.PersonenService;
import de.fi.webapp.service.PersonenServiceException;
import de.fi.webapp.service.SchweineService;
import de.fi.webapp.service.SchweineServiceException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/schweine")
@RequiredArgsConstructor
public class SchweineQueryController {

    private final SchweineService service;
    private final SchweinDtoMapper mapper;


    @Operation(summary = "Liefert ein Schwein")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Schwein gefunden",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SchweinDto.class)) }),
            @ApiResponse(responseCode = "400", description = "ungueltige ID",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Schwein nicht gefunden",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error",
                    content = @Content)})


    @GetMapping(path="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<SchweinDto> getIt(@PathVariable UUID id) throws SchweineServiceException {

        return ResponseEntity.of(service.findeSchweinNachId(id).map(mapper::convert));
    }

    @GetMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<SchweinDto>> getItAll( ) throws SchweineServiceException{




        return ResponseEntity.ok(mapper.convert(service.findeAlle()));
    }
}
