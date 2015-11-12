package com.boole.controller.rest.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.boole.domain.Language;
import com.boole.common.util.date.JodaDateTimeYearConverter;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on 10/22/2015.
 */
public class FilmDto extends AbstractDto {
    private String title;
    private String description;
    private DateTime releaseYear;
    private String rating;
    private Set<LanguageDto> languages;
    private Set<String> categories;
    private Set<ActorDto> actors;
    private String specialFeatures;

    public FilmDto() {
    }

    public FilmDto(Builder builder) {
        setId(builder.id);
        setLastUpdated(builder.lastUpdated);
        setTitle(builder.title);
        setDescription(builder.description);
        setReleaseYear(new DateTime(builder.releaseYear.getTime()));
        setRating(builder.rating);
        setLanguages(builder.languages);
        setSpecialFeatures(builder.specialFeatures);
    }

    public Set<ActorDto> getActors() {
        return actors;
    }

    public void setActors(Set<ActorDto> actors) {
        this.actors = actors;
    }

    public String getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonSerialize(using = JodaDateTimeYearConverter.class)
    public DateTime getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(DateTime releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Set<LanguageDto> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<LanguageDto> languages) {
        this.languages = languages;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    public static class Builder {
        public String specialFeatures;
        private int id;
        private DateTime lastUpdated;
        private String title;
        private String description;
        private Date releaseYear;
        private String rating;
        private Set<LanguageDto> languages = new HashSet<>(2);

        public Builder id(int filmId) {
            this.id = filmId;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder specialFeatures(String specialFeatures) {
            this.specialFeatures = specialFeatures;
            return this;
        }

        public Builder lastUpdated(DateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder releaseYear(Date releaseYear) {
            this.releaseYear = releaseYear;
            return this;
        }

        public Builder rating(String rating) {
            this.rating = rating;
            return this;
        }

        public FilmDto build() {
            return new FilmDto(this);
        }

        public Builder languages(Language language1, Language language2) {
            if (language1 != null) {
                languages.add(new LanguageDto.LanguageDtoBuilder().name(language1.getName())
                        .original(true)
                        .build());
            }
            if (language2 != null) {
                languages.add(new LanguageDto.LanguageDtoBuilder().name(language2.getName())
                        .original(false)
                        .build());
            }
            return this;
        }
    }
}
