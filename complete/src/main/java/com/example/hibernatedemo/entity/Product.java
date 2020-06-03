package com.example.hibernatedemo.entity;

import javax.persistence.*;

@Entity
//@Table(name = "Product")
//    @NamedEntityGraph(name = "products",
//            attributeNodes = {@NamedAttributeNode("gifts"),
//                    @NamedAttributeNode("suggestion"),
//                    @NamedAttributeNode("relative")}
//    )
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

//        @ManyToMany(cascade= CascadeType.ALL)
//        @JoinTable(
//                joinColumns = @JoinColumn( name="parent_id"),
//                inverseJoinColumns = @JoinColumn( name="id"))
//        private java.util.Set<Product> gifts;
//
//        @ManyToMany(cascade= CascadeType.ALL)
//        @JoinTable(
//                joinColumns = @JoinColumn( name="parent_id"),
//                inverseJoinColumns = @JoinColumn( name="id"))
//        private Set<Product> suggestion;
//
//        @ManyToMany(cascade= CascadeType.ALL)
//        @JoinTable(
//                joinColumns = @JoinColumn( name="parent_id"),
//                inverseJoinColumns = @JoinColumn( name="id"))
//        private Set<Product> relative;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}