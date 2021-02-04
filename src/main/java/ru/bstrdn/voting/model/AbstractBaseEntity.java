package ru.bstrdn.voting.model;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public class AbstractBaseEntity {
    public static final int START_SEQ = 100000;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "integer default nextval('global_seq')")
    private Integer id;
}