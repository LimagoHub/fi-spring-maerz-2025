package de.fi.webapp.persistence;

import de.fi.webapp.persistence.entity.CustomPersonRepository;
import de.fi.webapp.persistence.entity.PersonEntity;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CustomPersonRepositoryImpl implements CustomPersonRepository {
    private EntityManager em;

    @Override
    public void persist(final PersonEntity personEntity) {
        em.persist(personEntity);
    }
}
