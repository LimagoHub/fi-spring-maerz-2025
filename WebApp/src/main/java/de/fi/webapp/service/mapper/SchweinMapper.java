package de.fi.webapp.service.mapper;

import de.fi.webapp.persistence.entity.SchweinEntity;
import de.fi.webapp.service.model.Schwein;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchweinMapper {

    SchweinEntity convert(Schwein schwein);
    Schwein convert(SchweinEntity schweinEntity);

    Iterable<Schwein> convert(Iterable<SchweinEntity> schweins);
}
