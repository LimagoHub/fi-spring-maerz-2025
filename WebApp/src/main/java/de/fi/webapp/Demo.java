package de.fi.webapp;

import de.fi.webapp.persistence.PersonenRepository;
import de.fi.webapp.persistence.entity.PersonEntity;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Demo {
    private final PersonenRepository personenRepository;


    @PostConstruct
    public void foo() {
        var person = PersonEntity.builder().id(UUID.randomUUID()).vorname("Jane").nachname("Doe").build();



        var persons = personenRepository.findAll();
        persons.forEach(System.out::println);
    }
}
