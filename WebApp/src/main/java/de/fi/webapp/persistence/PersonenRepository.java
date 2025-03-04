package de.fi.webapp.persistence;

import de.fi.webapp.persistence.entity.CustomPersonRepository;
import de.fi.webapp.persistence.entity.PersonEntity;
import de.fi.webapp.persistence.entity.TinyPerson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PersonenRepository extends  CrudRepository<PersonEntity, UUID> {

    @Query("select p.vorname from PersonEntity p")
    Iterable<String> fritz();

    @Query("select new de.fi.webapp.persistence.entity.TinyPerson(p.id, p.nachname) from PersonEntity p")
    Iterable<TinyPerson> franz();

}
