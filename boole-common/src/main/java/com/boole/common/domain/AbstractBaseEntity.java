package com.boole.common.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * Created on 11/3/2015.
 */
@MappedSuperclass
public abstract class AbstractBaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;


    @Id
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Column(name = "created_at", nullable = false, length = 29)
    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "updated_at", nullable = false, length = 29)
    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
