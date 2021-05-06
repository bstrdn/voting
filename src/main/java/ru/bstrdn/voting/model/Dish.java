package ru.bstrdn.voting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
    @Column(name = "added", nullable = false, columnDefinition = "timestamp default now()")
    private Date added = new Date();

    @NotNull(message = "Error: There must be a restaurant")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonBackReference
    private Restaurant restaurant;
}
