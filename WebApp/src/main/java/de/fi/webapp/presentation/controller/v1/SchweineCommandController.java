package de.fi.webapp.presentation.controller.v1;


import de.fi.webapp.presentation.dto.SchweinDto;

import de.fi.webapp.presentation.mapper.SchweinDtoMapper;

import de.fi.webapp.service.SchweineService;
import de.fi.webapp.service.SchweineServiceException;
import de.fi.webapp.service.model.Schwein;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/v1/schweine")
@RequiredArgsConstructor
public class SchweineCommandController {
    private final SchweineService service;
    private final SchweinDtoMapper mapper;

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> removePerson(@PathVariable UUID id) throws SchweineServiceException {
      if(service.loesche(id))
          return ResponseEntity.ok().build();
      return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insertPerson(@Valid @RequestBody SchweinDto schweinDto, UriComponentsBuilder uriBuilder) throws SchweineServiceException {
        System.out.println(schweinDto);
        Schwein schwein = mapper.convertToSchwein(schweinDto);
        System.out.println("schwein: " + schwein);
        service.speichern(schwein);
        UriComponents uriComponents = uriBuilder.path("/v1/personen/{id}").buildAndExpand(schweinDto.getId());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatePerson(@PathVariable UUID id,@Valid @RequestBody SchweinDto schweinDto) throws SchweineServiceException {

        if(! id.equals(schweinDto.getId())) throw new IllegalArgumentException("Upps");

        service.speichern(mapper.convertToSchwein(schweinDto));

        return ResponseEntity.ok().build();
    }

    @PostMapping(path="/{id}/futtern")
    public ResponseEntity<Void> fuettern(@PathVariable UUID id) throws SchweineServiceException {
        if(service.fuettern(id))
            return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }
}
