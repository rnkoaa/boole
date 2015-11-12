package com.sakila.web.domain;
// Generated Nov 10, 2015 11:08:57 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Role generated by hbm2java
 */
@Entity
@Table(name = "roles", schema = "public")
public class Role implements java.io.Serializable {

    private Long id;
    private Crew crew;
    private Movie movie;
    private String job;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Role() {
    }

    public Role(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Role(Long id, Crew crew, Movie movie, String job, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.crew = crew;
        this.movie = movie;
        this.job = job;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crew_id")
    public Crew getCrew() {
        return this.crew;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    public Movie getMovie() {
        return this.movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Column(name = "job")
    public String getJob() {
        return this.job;
    }

    public void setJob(String job) {
        this.job = job;
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
