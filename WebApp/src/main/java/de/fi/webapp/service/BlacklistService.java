package de.fi.webapp.service;

import de.fi.webapp.service.model.Person;

import java.util.UUID;

public interface BlacklistService {

    boolean isBlacklisted(Person possibleBlacklistedPerson);
}
