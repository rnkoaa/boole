package com.boole.common.domain.dto;

import java.util.Objects;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/13/15.
 */
public class CrewDTO {
    private Long id;
    private String name;

    @Override
    public String toString() {
        return "CrewDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrewDTO crewDTO = (CrewDTO) o;
        return Objects.equals(id, crewDTO.id) &&
                Objects.equals(name, crewDTO.name);
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

}
