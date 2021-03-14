package ru.bstrdn.voting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
public class Dish extends AbstractNamedEntity {

//    todo change message
    @Getter
    @Setter
    @Min(value = 10, message = "МАЛО")
    @Max(value = 1000, message = "МНОГО")
//    @Range(min = 10, max = 1000, message = "НЕ ПРАВИЛЬНОЕ ЧИСЛО")
    @Column(name = "price", nullable = false)
    private int price;

    @Getter
    @Column(name = "added", nullable = false, columnDefinition = "timestamp default now()")
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date added = new Date();

//    @Getter
//    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
//    private boolean enabled = true;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonBackReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;
}
