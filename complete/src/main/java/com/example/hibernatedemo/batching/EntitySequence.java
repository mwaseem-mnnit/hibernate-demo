package com.example.hibernatedemo.batching;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by mohd.waseem on 03/02/20.
 */
@Entity
public class EntitySequence {

    @Id
    @GenericGenerator(
            name = "sequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "hibernate_sequence"),
                    @Parameter(name = "optimizer", value = "hilo"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "100")
            }
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequenceGenerator"
    )
    private Long id;

    @Column
    private String col1;

    @Column
    private String col2;

    public EntitySequence(String col1, String col2) {
        this.col1 = col1;
        this.col2 = col2;
    }

    public EntitySequence() {
    }

    public Long getId() {
        return this.id;
    }

    public String getCol1() {
        return this.col1;
    }

    public String getCol2() {
        return this.col2;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof EntitySequence)) return false;
        final EntitySequence other = (EntitySequence) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$col1 = this.getCol1();
        final Object other$col1 = other.getCol1();
        if (this$col1 == null ? other$col1 != null : !this$col1.equals(other$col1)) return false;
        final Object this$col2 = this.getCol2();
        final Object other$col2 = other.getCol2();
        if (this$col2 == null ? other$col2 != null : !this$col2.equals(other$col2)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof EntitySequence;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $col1 = this.getCol1();
        result = result * PRIME + ($col1 == null ? 43 : $col1.hashCode());
        final Object $col2 = this.getCol2();
        result = result * PRIME + ($col2 == null ? 43 : $col2.hashCode());
        return result;
    }

    public String toString() {
        return "EntitySequence(id=" + this.getId() + ", col1=" + this.getCol1() + ", col2=" + this.getCol2() + ")";
    }
}
