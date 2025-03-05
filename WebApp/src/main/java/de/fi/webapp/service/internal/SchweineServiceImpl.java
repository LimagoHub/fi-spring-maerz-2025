package de.fi.webapp.service.internal;

import de.fi.webapp.persistence.SchweineRepository;
import de.fi.webapp.service.PersonenService;
import de.fi.webapp.service.PersonenServiceException;
import de.fi.webapp.service.SchweineService;
import de.fi.webapp.service.SchweineServiceException;
import de.fi.webapp.service.mapper.SchweinMapper;
import de.fi.webapp.service.model.Schwein;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = SchweineServiceException.class, propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)

public class SchweineServiceImpl implements SchweineService {
    private final SchweineRepository schweineRepository;
    private final SchweinMapper mapper;



    @Override
    public void speichern(final Schwein schwein) throws SchweineServiceException {
        try {
            System.out.println(schwein);
            schweineRepository.save(mapper.convert(schwein));
        } catch (Exception e) {
            throw new SchweineServiceException("Oink",e);
        }
    }

    @Override
    public Optional<Schwein> findeSchweinNachId(final UUID id) throws SchweineServiceException {
        try {
            return schweineRepository.findById(id).map(mapper::convert);
        } catch (Exception e) {
            throw new SchweineServiceException("Oink",e);
        }
    }

    @Override
    public Iterable<Schwein> findeAlle() throws SchweineServiceException {
        try {
            return mapper.convert(schweineRepository.findAll());
        } catch (Exception e) {
            throw new SchweineServiceException("Oink",e);
        }
    }

    @Override
    public boolean loesche(final UUID id) throws SchweineServiceException {

        try {
            if (! schweineRepository.existsById(id))return false;
            schweineRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new SchweineServiceException("Oink",e);
        }
    }

    @Override
    public boolean fuettern(final UUID id) throws SchweineServiceException {
        var schweinOptional = findeSchweinNachId(id);
        if(schweinOptional.isEmpty()) return false;

        Schwein schwein = schweinOptional.get();
        schwein.fuettern();

        speichern(schwein);
        return true;


    }
}
