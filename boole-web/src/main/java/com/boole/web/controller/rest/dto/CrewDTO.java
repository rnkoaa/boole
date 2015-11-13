package com.boole.web.controller.rest.dto;

import java.util.Objects;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/13/15.
 */
public class CrewDTO {
    private Long id;
    private String name;
    private String role;

    @Override
    public String toString() {
        return "CrewDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
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

    @Override
    public int hashCode() {
        return Objects.hash(id, name, role);
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
}
