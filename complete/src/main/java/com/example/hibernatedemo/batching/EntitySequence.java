package com.example.hibernatedemo.batching;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by mohd.waseem on 03/02/20.
 */
@Entity
@Data
public class EntitySequence {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String col1;

    @Column
    private String col2;

    public EntitySequence(String col1, String col2) {
        this.col1 = col1;
        this.col2 = col2;
    }
}
