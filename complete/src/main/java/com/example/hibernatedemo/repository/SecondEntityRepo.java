package com.example.hibernatedemo.repository;

import com.example.hibernatedemo.entity.oneToOne.nonPrimaryKey.SecondEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mohd.waseem on 09/06/20.
 */
public interface SecondEntityRepo extends JpaRepository<SecondEntity, Long> {
}
