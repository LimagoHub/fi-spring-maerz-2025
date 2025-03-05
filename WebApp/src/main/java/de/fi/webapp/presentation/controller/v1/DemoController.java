package de.fi.webapp.presentation.controller.v1;


import de.fi.webapp.aspects.Dozent;
import de.fi.webapp.presentation.dto.PersonDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/demo")
@Dozent
public class DemoController {

    @GetMapping(path="/gruss", produces = MediaType.TEXT_PLAIN_VALUE)
    public String gruss() {
        return "Hallo Rest!";
    }
    @GetMapping(path="/john", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto getIt() {
        return PersonDto.builder().id(UUID.randomUUID()).vorname("John").nachname("Doe").build();
    }
}
