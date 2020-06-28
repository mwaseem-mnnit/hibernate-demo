package com.example.hibernatedemo.entity.oneToOne.nonPrimaryKey;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Created by mohd.waseem on 09/06/20.
 */
@Entity
@Getter
@Setter
public class SecondEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "first_entity_code", referencedColumnName = "code")
    private FirstEntity firstEntity;

    private String value;
}
