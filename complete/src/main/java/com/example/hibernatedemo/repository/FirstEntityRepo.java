package com.example.hibernatedemo.repository;

import com.example.hibernatedemo.entity.oneToOne.nonPrimaryKey.FirstEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by mohd.waseem on 09/06/20.
 */
public interface FirstEntityRepo extends JpaRepository<FirstEntity, Long> {
    FirstEntity findByCode(String code);

    @Query("select F from FirstEntity F join SecondEntity S on S.firstEntity.code = :code and F.code=:code")
    FirstEntity findByCodeCustom(@Param("code") String code);

    @Query(value = "select F.* from FirstEntity F left join SecondEntity S on S.first_entity_code = F.code and F.code=:code", nativeQuery = true)
    FirstEntity findByCodeCustomNative(@Param("code") String code);
}
