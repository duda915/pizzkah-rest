package com.mdud.pizzkahrest.datamodel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "manager")
@Data
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    private String privileges;

    public Manager(){}
    public Manager(String username, String password, String privileges) {
        this.username = username;
        this.password = password;
        this.privileges = privileges;
    }
}
