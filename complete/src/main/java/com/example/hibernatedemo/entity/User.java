package com.example.hibernatedemo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

	@Column
    private String name;

	@Column
    private String email;

    @Column(name = "test")
    private Boolean test;

    @Version
	@Column(name = "version")
	private Integer version;

    @OneToMany( fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Boolean getTest() {
		return test;
	}

	public void setTest(Boolean test) {
		this.test = test;
	}
}
