package com.example.hibernatedemo.entity.mapping.error;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class LoginInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    @MapsId
    private Login login;
}
