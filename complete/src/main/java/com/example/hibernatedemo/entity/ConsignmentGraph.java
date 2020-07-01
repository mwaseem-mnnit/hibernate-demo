package com.example.hibernatedemo.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Consignment")
public class ConsignmentGraph {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne( mappedBy = "consignmentGraph")
    @Fetch(FetchMode.JOIN)
    private Vas1 vas1;

    @OneToOne( mappedBy = "consignmentGraph")
    @Fetch(FetchMode.JOIN)
    private Vas2 vas2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vas1 getVas1() {
        return vas1;
    }

    public void setVas1(Vas1 vas1) {
        this.vas1 = vas1;
    }

    public Vas2 getVas2() {
        return vas2;
    }

    public void setVas2(Vas2 vas2) {
        this.vas2 = vas2;
    }
}
