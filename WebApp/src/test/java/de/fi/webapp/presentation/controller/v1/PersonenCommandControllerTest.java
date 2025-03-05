package de.fi.webapp.presentation.controller.v1;

import de.fi.webapp.service.PersonenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
//@Sql({"/create.sql", "/insert.sql"})
class PersonenCommandControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @MockitoBean
    private PersonenService serviceMock;

    @Test
    void test1() throws Exception {
        when(serviceMock.loesche(any(UUID.class))).thenReturn(true);

        ResponseEntity<Void> response = restTemplate.exchange("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", HttpMethod.DELETE,null ,Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(serviceMock, times(1)).loesche(UUID.fromString("b2e24e74-8686-43ea-baff-d9396b4202e0"));

    }

    @Test
    void test2() throws Exception {
        when(serviceMock.loesche(any(UUID.class))).thenReturn(false);

        ResponseEntity<Void> response = restTemplate.exchange("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", HttpMethod.DELETE,null ,Void.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(serviceMock, times(1)).loesche(UUID.fromString("b2e24e74-8686-43ea-baff-d9396b4202e0"));

    }
}