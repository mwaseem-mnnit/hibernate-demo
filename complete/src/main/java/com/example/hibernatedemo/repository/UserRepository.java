package com.example.hibernatedemo.repository;

import com.example.hibernatedemo.dto.UserDTO;
import com.example.hibernatedemo.dto.UserProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.hibernatedemo.entity.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "select u.count as u_count from User u where u.id = :id ", nativeQuery = true)
    Integer getCount(@Param("id") Integer id);

    @Query(value = "select new com.example.hibernatedemo.dto.UserDTO( u.name, u.email) from User u, Comment c where c.id = u.id and u.id in :ids")
    List<UserDTO> getUser(List<Integer> ids);

    @Query(value = "select  u.name, u.email, c.comment from User u join Comment c on u.id = c.user_id where u.id in :ids", nativeQuery = true)
    List<UserProjection> getUserInterface(List<Integer> ids);

    User findByName(String name);
}
