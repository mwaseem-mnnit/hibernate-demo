package com.example.hibernatedemo.entity.manyToOne.nonPrimaryKey;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.NaturalId;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
public class Shipment implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column( unique = true)
    @NaturalId
    private String shipmentCode;

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL)
    @BatchSize(size = 2)
    private List<Order> orders;
}
