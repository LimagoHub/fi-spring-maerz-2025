package de.fi.webapp.presentation.controller.v1;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/demo")
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
