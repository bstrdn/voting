package ru.bstrdn.voting.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
public class User extends AbstractBaseEntity{


    String name;
}
