package de.fi.webapp.presentation.controller.v1;

import de.fi.webapp.presentation.dto.PersonDto;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/v1/personen")
public class PersonenCommandController {

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> removePerson(@PathVariable UUID id) {
        if(id.toString().endsWith("fa6"))
            return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insertPerson(@Valid @RequestBody PersonDto personDto, UriComponentsBuilder uriBuilder) {
        System.out.println(personDto + "wurde erfolgreich gespeichert");
        UriComponents uriComponents = uriBuilder.path("/v1/personen/{id}").buildAndExpand(personDto.getId());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatePerson(@PathVariable UUID id,@Valid @RequestBody PersonDto personDto) {
        System.out.println(personDto + "wurde erfolgreich updated");

        return ResponseEntity.ok().build();
    }
}
