package com.example.hibernatedemo.entity.identifier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.UUID;

/**
 * Created by mohd.waseem on 30/06/20.
 */
@Entity
@Table(name = "uuid_generator_demo", uniqueConstraints = {
        @UniqueConstraint(columnNames = "test_column")
})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UUIDGeneratorDemo {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @Column(name = "test_column")
    private String testColumn;
}
