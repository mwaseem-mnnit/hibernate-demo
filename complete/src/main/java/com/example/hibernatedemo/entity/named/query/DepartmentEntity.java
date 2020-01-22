package com.example.hibernatedemo.entity.named.query;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DEPARTMENT")
@NamedQueries({@NamedQuery(name=DepartmentEntity.GET_DEPARTMENT_BY_ID, query=DepartmentEntity.GET_DEPARTMENT_BY_ID_QUERY),})
@FilterDef(name="deptFilter", parameters={@ParamDef( name="name", type="string")})
@Filters( {@Filter(name="deptFilter", condition=":name = name")})
public class DepartmentEntity implements Serializable {

    static final String GET_DEPARTMENT_BY_ID_QUERY = "from DepartmentEntity d where d.id = :id";
    public static final String GET_DEPARTMENT_BY_ID = "GET_DEPARTMENT_BY_ID";
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "NAME", unique = true, nullable = false, length = 100)
    private String name;

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
}
