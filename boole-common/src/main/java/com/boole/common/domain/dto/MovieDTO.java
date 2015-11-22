package com.boole.common.domain.dto;

import com.boole.common.domain.util.JSR310DateSerializer;
import com.boole.common.util.StringUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/13/15.
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class MovieDTO implements Serializable {

    Set<CrewDTO> actors = new HashSet<>(0);
    Set<CrewDTO> directors = new HashSet<>(0);
    Set<CrewDTO> crews = new HashSet<>(0);
    Set<CrewDTO> producers = new HashSet<>(0);
    private Long id;
    private String name;
    private String synopsis;
    private String country;
    private Integer year;
    private Double review;
    private String rating;
    private Integer runtime;
    private String language;
    private ZonedDateTime releaseDate;
    private Set<GenreDTO> genres = new HashSet<>(0);
    private Set<CrewDTO> writers = new HashSet<>(0);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        if (!StringUtil.isNullOrEmpty(name)) {
            if ((name.length() > 19)) {
                name = StringUtil.cropWholeWords(name, 19);
                name = name + "...";
            }
            name = StringUtil.capitalizeEachWord(name);
            return name;
        }
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSynopsis() {
        if (!StringUtil.isNullOrEmpty(synopsis)) {
            synopsis = StringUtil.cropWholeWords(synopsis, 220);
            synopsis = synopsis + " ...";
            return synopsis;
        }
        return null;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getReview() {
        return review;
    }

    public void setReview(Double review) {
        this.review = review;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    //customize the format of this date
    @JsonSerialize(using = JSR310DateSerializer.class)
    public ZonedDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(ZonedDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<CrewDTO> getActors() {
        return actors;
    }

    public void setActors(Set<CrewDTO> actors) {
        this.actors = actors;
    }

    public Set<CrewDTO> getDirectors() {
        return directors;
    }

    public void setDirectors(Set<CrewDTO> directors) {
        this.directors = directors;
    }

    public Set<CrewDTO> getCrews() {
        return crews;
    }

    public void setCrews(Set<CrewDTO> crews) {
        this.crews = crews;
    }

    public Set<CrewDTO> getProducers() {
        return producers;
    }

    public void setProducers(Set<CrewDTO> producers) {
        this.producers = producers;
    }

    public Set<GenreDTO> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreDTO> genres) {
        this.genres = genres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDTO movieDTO = (MovieDTO) o;
        return Objects.equals(id, movieDTO.id) &&
                Objects.equals(name, movieDTO.name) &&
                Objects.equals(year, movieDTO.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, year);
    }

    @Override
    public String toString() {
        return "MovieDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", country='" + country + '\'' +
                ", year=" + year +
                ", review=" + review +
                ", rating='" + rating + '\'' +
                ", runtime=" + runtime +
                ", language='" + language + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }

    public Set<CrewDTO> getWriters() {
        return writers;
    }

    public void setWriters(Set<CrewDTO> writers) {
        this.writers = writers;
    }
}
