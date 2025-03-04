package de.fi.webapp.presentation.controller.v1;

import de.fi.webapp.presentation.dto.PersonDto;
import de.fi.webapp.presentation.mapper.PersonDtoMapper;
import de.fi.webapp.service.PersonenService;
import de.fi.webapp.service.PersonenServiceException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/v1/personen")
@RequiredArgsConstructor
public class PersonenCommandController {
    private final PersonenService service;
    private final PersonDtoMapper mapper;

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> removePerson(@PathVariable UUID id) throws PersonenServiceException {
      if(service.loesche(id))
          return ResponseEntity.ok().build();
      return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insertPerson(@Valid @RequestBody PersonDto personDto, UriComponentsBuilder uriBuilder) throws PersonenServiceException {
        service.speichern(mapper.convert(personDto));
        UriComponents uriComponents = uriBuilder.path("/v1/personen/{id}").buildAndExpand(personDto.getId());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatePerson(@PathVariable UUID id,@Valid @RequestBody PersonDto personDto) throws PersonenServiceException {

        if(! id.equals(personDto.getId())) throw new IllegalArgumentException("Upps");

        service.speichern(mapper.convert(personDto));

        return ResponseEntity.ok().build();
    }
}
