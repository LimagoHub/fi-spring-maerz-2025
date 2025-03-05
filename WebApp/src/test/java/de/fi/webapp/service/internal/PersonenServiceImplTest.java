package de.fi.webapp.service.internal;

import de.fi.webapp.persistence.PersonenRepository;
import de.fi.webapp.service.PersonenServiceException;
import de.fi.webapp.service.mapper.PersonMapper;
import de.fi.webapp.service.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonenServiceImplTest {

    @InjectMocks
    private PersonenServiceImpl objectUnderTest;

    @Mock
    private PersonenRepository personenRepositoryMock;
    @Mock
    private PersonMapper personMapperMock;
    @Mock
    private List<String> personMock;


    @Test
    void speichern_parameternull_throwsPersonenServiceException() throws PersonenServiceException {
        // Arrange

        // action + Assertion




        PersonenServiceException ex = assertThrows(PersonenServiceException.class,()->objectUnderTest.speichern(null));
        assertEquals("Person darf nicht null sein", ex.getMessage());

    }
}