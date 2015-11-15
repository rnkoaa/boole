package com.boole.common.domain.dto;

import com.boole.common.domain.Movie;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/13/15.
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CrewDTO {
    private Long id;
    private String name;
    private String role;
    private Movie movie;
    private List<RoleDto> movieRoles = new ArrayList<>(0);

    @Override
    public String toString() {
        return "CrewDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", name='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrewDTO crewDTO = (CrewDTO) o;
        return Objects.equals(id, crewDTO.id) &&
                Objects.equals(name, crewDTO.name) &&
                Objects.equals(role, crewDTO.role);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovieRoles(List<RoleDto> movieRoles) {
        this.movieRoles = movieRoles;
    }

    public List<RoleDto> getMovieRoles() {
        return movieRoles;
    }
}
