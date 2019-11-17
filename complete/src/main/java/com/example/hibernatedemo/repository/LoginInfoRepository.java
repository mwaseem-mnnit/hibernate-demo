package com.example.hibernatedemo.repository;

import com.example.hibernatedemo.entity.mapping.error.LoginInfo;
import org.springframework.data.repository.CrudRepository;

public interface LoginInfoRepository extends CrudRepository<LoginInfo, Integer> {
}
