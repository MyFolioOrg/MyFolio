package me.may.myfolio.common.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    public long id;
    public String username;
    public String emailaddress;
    public String password;
}
