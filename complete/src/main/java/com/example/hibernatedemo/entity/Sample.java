package com.example.hibernatedemo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by mohd.waseem on 06/03/20.
 */
@Entity
public class Sample {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String sample;
}
