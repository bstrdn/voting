package ru.bstrdn.voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Getter
@Setter
public class Dish extends AbstractNamedEntity {

    @Min(value = 10, message = "Error: the price should be higher 10")
    @Max(value = 10000, message = "Error: the price should be less 10000")
    @Column(name = "price", nullable = false)
    private int price;

    @Setter(AccessLevel.NONE)
    @Column(name = "added", nullable = false, columnDefinition = "date default now()")
    private Date added = new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonIgnore
    private Restaurant restaurant;
}
