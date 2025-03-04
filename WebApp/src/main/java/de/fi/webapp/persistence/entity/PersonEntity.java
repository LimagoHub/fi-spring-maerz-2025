package de.fi.webapp.persistence.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_personen")
public class PersonEntity {

    @Id
    private UUID id;

    @Column(length = 50, nullable = false)
    private String vorname;

    @Column(length = 50, nullable = false)
    private String nachname;


}
