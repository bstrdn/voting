package ru.bstrdn.voting.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Restaurant extends AbstractBaseEntity {

    @OneToMany(mappedBy = "restaurant")
    @JsonManagedReference
    List<Menu> menu;

}
