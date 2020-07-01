package com.example.hibernatedemo.entity.multiplebagfetch;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy="person",fetch= FetchType.LAZY, cascade = CascadeType.ALL)
//    @OrderColumn(name = "auto_order")
    private List<Auto> autos;
}
