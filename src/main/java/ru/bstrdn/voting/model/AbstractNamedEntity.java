package ru.bstrdn.voting.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ToString
@MappedSuperclass
public class AbstractNamedEntity extends AbstractBaseEntity {

    @Getter
    @Setter
//    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "name", nullable = false)
//    @SafeHtml(groups = {View.Web.class}, whitelistType = NONE)
    protected String name;

    protected AbstractNamedEntity() {
    }

    protected AbstractNamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }
}
