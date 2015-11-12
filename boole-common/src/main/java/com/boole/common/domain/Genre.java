package com.boole.common.domain;
// Generated Nov 10, 2015 11:08:57 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Genre generated by hbm2java
 */
@Entity
@Table(name = "genres", schema = "public")
public class Genre implements java.io.Serializable {

    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToMany(mappedBy = "genres")
    private Set<Movie> movies = new HashSet<>(0);

    public Genre() {
    }

    public Genre(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Genre(Long id, String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Id

    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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