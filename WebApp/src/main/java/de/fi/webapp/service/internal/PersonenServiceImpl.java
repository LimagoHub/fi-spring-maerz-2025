package de.fi.webapp.service.internal;

import de.fi.webapp.persistence.PersonenRepository;
import de.fi.webapp.service.PersonenService;
import de.fi.webapp.service.PersonenServiceException;
import de.fi.webapp.service.mapper.PersonMapper;
import de.fi.webapp.service.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = PersonenServiceException.class, propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
public class PersonenServiceImpl implements PersonenService {

    private final PersonenRepository personenRepository;
    private final PersonMapper personMapper;
    @Qualifier("Antipathen")
    private final List<String> anthipathen;

    @Override
    public void speichern(final Person person) throws PersonenServiceException {
        try {
            if(person == null) throw new PersonenServiceException("Person darf nicht null sein");
            if(person.getVorname() == null || person.getVorname().length() < 2) throw new PersonenServiceException("Vorname zu kurz");
            if(person.getNachname() == null || person.getNachname().length() < 2) throw new PersonenServiceException("Nachname zu kurz");

            if(anthipathen.contains(person.getVorname()) )throw new PersonenServiceException("Antipath");

            personenRepository.save(personMapper.convert(person));
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Fehler beim Speichern der Person",e);
        }
    }

    @Transactional(rollbackFor = PersonenServiceException.class, propagation = Propagation.REQUIRED,isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public Optional<Person> findePersonNachId(final UUID id) throws PersonenServiceException {
        try {
            return personenRepository.findById(id).map(personMapper::convert);
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Ein Fehler ist aufgetreten",e);
        }
    }
    @Override
    public Iterable<Person> findeAlle() throws PersonenServiceException {
        try {
            return personMapper.convert(personenRepository.findAll());
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Ein Fehler ist aufgetreten",e);
        }
    }

    @Override
    public boolean loesche(final UUID id) throws PersonenServiceException {
        try {
            if(! personenRepository.existsById(id)) return false;

            personenRepository.deleteById(id);

            return true;
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Ein Fehler ist aufgetreten",e);
        }
    }

}
