package com.example.hibernatedemo.service;

import com.example.hibernatedemo.dao.CommentDao;
import com.example.hibernatedemo.entity.Comment;
import com.example.hibernatedemo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private CommentRepository commentRepository;

//    @Transactional( propagation = Propagation.REQUIRED)
    public Comment getCommentById(Integer id) {
        return commentDao.getById(id);
    }

    //@Transactional( propagation = Propagation.REQUIRES_NEW)
    public Comment create( String comment) {
        Comment comment1 = new Comment();
        comment1.setComment(comment);
//        return commentRepository.save(comment1);
        return commentDao.create(comment1);
    }
}
