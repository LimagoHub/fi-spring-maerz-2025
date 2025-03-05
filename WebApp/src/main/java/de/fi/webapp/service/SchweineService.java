package de.fi.webapp.service;


import de.fi.webapp.service.model.Schwein;

import java.util.Optional;
import java.util.UUID;

public interface SchweineService {

    void speichern(Schwein schwein) throws SchweineServiceException;
    Optional<Schwein> findeSchweinNachId(UUID id) throws SchweineServiceException;
    Iterable<Schwein> findeAlle() throws SchweineServiceException;
    boolean loesche(UUID id) throws SchweineServiceException;
    boolean fuettern(UUID id) throws SchweineServiceException;
}
