package ru.bstrdn.voting.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Restaurant extends AbstractNamedEntity {

    @Getter
    @Setter
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Dish> dishes;

    public Restaurant(Integer id) {
        super(id);
    }

    public Restaurant(String name, List<Dish> dishes) {
        super(name);
        this.dishes = dishes;
    }
}
