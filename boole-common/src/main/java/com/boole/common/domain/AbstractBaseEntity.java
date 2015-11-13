package com.boole.common.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created on 11/3/2015.
 */
@MappedSuperclass
public abstract class AbstractBaseEntity  implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @Id
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, length = 29)
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false, length = 29)
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
