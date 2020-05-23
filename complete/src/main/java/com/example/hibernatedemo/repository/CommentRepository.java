package com.example.hibernatedemo.repository;

import com.example.hibernatedemo.entity.Comment;
import com.example.hibernatedemo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CommentRepository extends CrudRepository<Comment, Integer> {

    Comment findByComment(String comment);

    Comment findByUser(User user);

    @Query("Select c from Comment c where c.user.id = :userId")
    Comment findByUser( @Param("userId") Integer userId);
}
