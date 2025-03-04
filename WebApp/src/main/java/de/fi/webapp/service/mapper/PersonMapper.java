package de.fi.webapp.service.mapper;

import de.fi.webapp.persistence.entity.PersonEntity;
import de.fi.webapp.service.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonEntity convert(Person person);
    Person convert(PersonEntity entity);
    Iterable<Person> convert(Iterable<PersonEntity> persons);
 }
