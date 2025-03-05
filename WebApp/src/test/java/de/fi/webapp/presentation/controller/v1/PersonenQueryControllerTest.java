package de.fi.webapp.presentation.controller.v1;

import de.fi.webapp.presentation.dto.PersonDto;
import de.fi.webapp.service.PersonenService;
import de.fi.webapp.service.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
//@Sql({"/create.sql", "/insert.sql"})
class PersonenQueryControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @MockitoBean
    private PersonenService serviceMock;

    @Test
    void test1() throws Exception {
        var optPerson = Optional.of(Person.builder().id(UUID.randomUUID()).vorname("John").nachname("Doe").build());
        when(serviceMock.findePersonNachId(UUID.fromString("b2e24e74-8686-43ea-baff-d9396b4202e0"))).thenReturn(optPerson);

        PersonDto dto = restTemplate.getForObject("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDto.class);

        assertEquals("John", dto.getVorname());
    }

    @Test
    void test2() throws Exception {
        var optPerson = Optional.of(Person.builder().id(UUID.randomUUID()).vorname("John").nachname("Doe").build());
        when(serviceMock.findePersonNachId(UUID.fromString("b2e24e74-8686-43ea-baff-d9396b4202e0"))).thenReturn(optPerson);

        String string = restTemplate.getForObject("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", String.class);

        System.out.println(string);
    }

    @Test
    void test3() throws Exception {
        var optPerson = Optional.of(Person.builder().id(UUID.randomUUID()).vorname("John").nachname("Doe").build());
        when(serviceMock.findePersonNachId(UUID.fromString("b2e24e74-8686-43ea-baff-d9396b4202e0"))).thenReturn(optPerson);

        ResponseEntity<PersonDto> entity = restTemplate.getForEntity("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDto.class);

        PersonDto dto = entity.getBody();
        assertEquals("John", dto.getVorname());
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    void test4() throws Exception {

        PersonDto dto = PersonDto.builder().id(UUID.randomUUID()).vorname("John").nachname("Doe").build();

        HttpEntity requestEntity = new HttpEntity(dto);

        var personen = List.of(Person.builder().id(UUID.randomUUID()).vorname("John").nachname("Doe").build(),Person.builder().id(UUID.randomUUID()).vorname("John").nachname("Rambo").build());

        when(serviceMock.findeAlle()).thenReturn(personen);

        ResponseEntity<List<PersonDto>> entity = restTemplate.exchange("/v1/personen", HttpMethod.GET,requestEntity,new ParameterizedTypeReference<List<PersonDto>>() { });

        List<PersonDto> dto = entity.getBody();
        assertEquals(2, dto.size());
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }
}