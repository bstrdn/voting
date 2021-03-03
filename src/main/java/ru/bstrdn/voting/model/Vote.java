package ru.bstrdn.voting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Vote extends AbstractBaseEntity{

    @Column(name = "voted"
    )
private Date voted = new Date();

}
