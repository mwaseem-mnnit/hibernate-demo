package com.example.hibernatedemo.repository;

import com.example.hibernatedemo.entity.Comment;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CommentRepository extends CrudRepository<Comment, Integer> {

    Comment findByComment(String comment);
}
