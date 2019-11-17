package com.example.hibernatedemo.repository;

import com.example.hibernatedemo.entity.mapping.error.Login;
import org.springframework.data.repository.CrudRepository;

public interface LoginRepository extends CrudRepository<Login, Integer> {
}
