package ru.bstrdn.voting.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@MappedSuperclass
@Getter
@Setter
public class AbstractNamedEntity extends AbstractBaseEntity {

    @NotNull(message = "Error: Name must not be null")
    @Size(min = 2, max = 100, message = "Error: Name must be between 2 and 100 characters")
    @Column(name = "name", nullable = false)
    protected String name;

    protected AbstractNamedEntity() {
    }

    protected AbstractNamedEntity(Integer id) {
        super(id);
    }
    protected AbstractNamedEntity(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString() + '(' + name + ')';
    }
}
