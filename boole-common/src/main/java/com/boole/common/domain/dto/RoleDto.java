package com.boole.common.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/14/15.
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class RoleDto {
    private MovieDTO movie;
    private String role;

    public RoleDto() {
    }

    public RoleDto(MovieDTO movieDTO, String role) {
        this.movie = movieDTO;
        this.role = role;
    }

    public MovieDTO getMovie() {
        return movie;
    }

    public String getRole() {
        return role;
    }

    public void setMovie(MovieDTO movie) {
        this.movie = movie;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
