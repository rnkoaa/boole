package com.boole.controller.rest.dto;

import org.joda.time.DateTime;

import java.util.Set;

/**
 * Created on 10/22/2015.
 */
public class ActorDto extends PersonDto {

    private Set<FilmDto> films;

    public ActorDto() {
    }

    public ActorDto(Builder builder) {
        setFirstName(builder.firstName);
        setLastName(builder.lastName);
        setId(builder.id);
        setFilms(builder.films);
        setLastUpdated(builder.lastUpdated);
    }

    public Set<FilmDto> getFilms() {
        return films;
    }

    public void setFilms(Set<FilmDto> films) {
        this.films = films;
    }

    public static class Builder {
        public Set<FilmDto> films;
        public DateTime lastUpdated;
        private int id;
        private String firstName;
        private String lastName;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder films(Set<FilmDto> films) {
            if (films != null && films.size() > 0)
                this.films = films;
            return this;
        }

        public Builder lastUpdated(DateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public ActorDto build() {
            return new ActorDto(this);
        }
    }
}
