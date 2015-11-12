package com.boole.index.domain;

import com.boole.domain.Film;
import com.boole.domain.FilmActor;
import com.boole.domain.FilmCategory;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/5/15.
 */
public class IndexFilm extends AbstractIndexEntity {
    private String title;
    private String description;
    private long releaseYear;
    private IndexLanguage originalLanguage;
    private IndexLanguage language;
    private BigDecimal replacementCost;
    private long length;
    private String rating;
    private String specialFeatures;
    private Set<IndexActor> actors;
    private Set<IndexCategory> categories;
    private BigDecimal rentalRate;

    public IndexFilm() {
    }

    public IndexFilm(Film film) {
        setTitle(film.getTitle());
        setDescription(film.getDescription());
        setId(film.getFilmId());
        setReleaseYear((film.getReleaseYear() != null)
                ? new DateTime(film.getReleaseYear().getTime()).getYear()
                : 1900);
        setRating(film.getRating());
        setSpecialFeatures(film.getSpecialFeatures());
        if (film.getLanguage1() != null) {
            setOriginalLanguage(new IndexLanguage(film.getLanguage1()));
        }
        if (film.getLanguage2() != null) {
            setLanguage(new IndexLanguage(film.getLanguage2()));
        }
        setLength(film.getLength());
        setSpecialFeatures(film.getSpecialFeatures());
        setFilmActors((film.getFilmActors().size() > 0)
                ? film.getFilmActors()
                : new HashSet<>(0));
        setFilmCategories((film.getFilmCategories().size() > 0)
                ? film.getFilmCategories()
                : new HashSet<>(0));
        setCreated((film.getLastUpdated() != null)
                ? new DateTime(film.getLastUpdated().getTime())
                : new DateTime());
        setUpdated((film.getLastUpdated() != null)
                ? new DateTime(film.getLastUpdated().getTime())
                : new DateTime());

        setReplacementCost(film.getReplacementCost());
        setRentalRate(film.getRentalRate());

    }

    public BigDecimal getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(BigDecimal replacementCost) {
        this.replacementCost = replacementCost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public IndexLanguage getLanguage() {
        return language;
    }

    public void setLanguage(IndexLanguage language) {
        this.language = language;
    }

    public Set<IndexActor> getActors() {
        return actors;
    }

    public void setActors(Set<IndexActor> actors) {
        this.actors = actors;
    }

    public void setFilmActors(Set<FilmActor> actors) {
        setActors(actors.stream()
                .map(filmActor -> new IndexActor(filmActor.getActor()))
                .collect(Collectors.toSet()));

    }

    public void setFilmCategories(Set<FilmCategory> filmCategories) {
        setCategories(filmCategories.stream()
                .map(filmCategory -> new IndexCategory(filmCategory.getCategory()))
                .collect(Collectors.toSet()));
    }

    public Set<IndexCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<IndexCategory> categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(long releaseYear) {
        this.releaseYear = releaseYear;
    }

    public IndexLanguage getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(IndexLanguage originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(BigDecimal rentalRate) {
        this.rentalRate = rentalRate;
    }
}
