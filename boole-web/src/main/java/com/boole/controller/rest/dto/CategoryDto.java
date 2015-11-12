package com.boole.controller.rest.dto;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.Set;

/**
 * Created on 10/22/2015.
 */
public class CategoryDto extends AbstractDto {
    private String name;
    private Set<FilmDto> films;

    public CategoryDto() {
    }

    public CategoryDto(Builder builder) {
        setId(builder.id);
        setLastUpdated(new DateTime(builder.lastUpdated.getTime()));
        this.name = builder.name;
        setFilms(builder.films);
    }

    public String getName() {
        return name;
    }

    public Set<FilmDto> getFilms() {
        return films;
    }

    public void setFilms(Set<FilmDto> films) {
        this.films = films;
    }

    public static class Builder {
        public Set<FilmDto> films;
        private Integer id;
        private String name;
        private Date lastUpdated;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder films(Set<FilmDto> films) {
            this.films = films;
            return this;
        }

        public Builder lastUpdated(Date lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        public CategoryDto build() {
            return new CategoryDto(this);
        }
    }
}
