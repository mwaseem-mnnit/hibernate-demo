package com.example.hibernatedemo.batching;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by mohd.waseem on 03/02/20.
 */
public interface EntityIdentityRepository extends JpaRepository<EntityIdentity,Long> {
    @Query(value = "select * from EntityIdentity EI where EI.col1 = :col1", nativeQuery = true)
    EntityIdentity findByCol1(@Param("col1") Long col1);
}
