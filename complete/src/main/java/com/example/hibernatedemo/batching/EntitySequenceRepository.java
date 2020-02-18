package com.example.hibernatedemo.batching;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mohd.waseem on 03/02/20.
 */
public interface EntitySequenceRepository extends JpaRepository<EntitySequence, Long> {
}
