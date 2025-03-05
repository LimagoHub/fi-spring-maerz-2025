package de.fi.webapp.service.config;


import de.fi.webapp.persistence.PersonenRepository;
import de.fi.webapp.service.PersonenService;
import de.fi.webapp.service.internal.PersonenServiceImpl;
import de.fi.webapp.service.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.List;

@Configuration
public class PersonConfig {


    @Bean
    @Scope("singleton")
    @Qualifier("Antipathen")
    public List<String> antipathen() {
        return List.of("Attila", "Peter", "Paul", "Mary");
    }

    @Bean
    @Scope("singleton")
    @Qualifier("Fruits")
    public List<String> fruits() {
        return List.of("Banaa", "Apple", "Cherry", "Raspberry");
    }

    @Bean
    public PersonenService personenService(final PersonenRepository personenRepository, final PersonMapper mapper,    @Qualifier("Antipathen") final List<String> antipathen) {
        return new PersonenServiceImpl(personenRepository, mapper, antipathen);
    }
}
