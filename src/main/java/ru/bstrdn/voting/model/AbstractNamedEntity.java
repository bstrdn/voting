package ru.bstrdn.voting.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ToString
@MappedSuperclass
public class AbstractNamedEntity extends AbstractBaseEntity {

    @Getter
    @Setter
    @NotNull(message = "Название не должно равняться null")
    @Size(min = 2, max = 100, message = "Название должно быть от 2х до 100 символов")
    @Column(name = "name", nullable = false)
//    @SafeHtml(groups = {View.Web.class}, whitelistType = NONE)
    protected String name;

    protected AbstractNamedEntity() {
    }

    protected AbstractNamedEntity(Integer id) {
        super(id);
    }

    protected AbstractNamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }
}
