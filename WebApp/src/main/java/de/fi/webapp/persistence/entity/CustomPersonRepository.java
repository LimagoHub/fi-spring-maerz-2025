package de.fi.webapp.persistence.entity;

import de.fi.webapp.service.PersonenServiceException;
import de.fi.webapp.service.model.Person;

public interface CustomPersonRepository {

    void persist(PersonEntity personEntity);
}
