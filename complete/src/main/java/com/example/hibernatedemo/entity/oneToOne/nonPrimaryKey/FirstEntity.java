package com.example.hibernatedemo.entity.oneToOne.nonPrimaryKey;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

/**
 * Created by mohd.waseem on 09/06/20.
 */
@Entity
@Getter
@Setter
public class FirstEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String code;

    @OneToOne(mappedBy = "firstEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private SecondEntity secondEntity;

}
