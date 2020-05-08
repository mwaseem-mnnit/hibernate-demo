package com.example.hibernatedemo.entity;

import javax.persistence.*;
import java.util.Set;

    @Entity
    @NamedEntityGraph(name = "products",
            attributeNodes = {@NamedAttributeNode("gifts"),
                    @NamedAttributeNode("suggestion"),
                    @NamedAttributeNode("relative")}
    )
    public class Product
    {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(name = "name")
        private String name;

        @ManyToMany(cascade= CascadeType.ALL)
        @JoinTable(
                joinColumns = @JoinColumn( name="parent_id"),
                inverseJoinColumns = @JoinColumn( name="id"))
        private java.util.Set<Product> gifts;

        @ManyToMany(cascade= CascadeType.ALL)
        @JoinTable(
                joinColumns = @JoinColumn( name="parent_id"),
                inverseJoinColumns = @JoinColumn( name="id"))
        private Set<Product> suggestion;

        @ManyToMany(cascade= CascadeType.ALL)
        @JoinTable(
                joinColumns = @JoinColumn( name="parent_id"),
                inverseJoinColumns = @JoinColumn( name="id"))
        private Set<Product> relative;
    }