package com.sakila.controller.rest.dto;

import org.joda.time.DateTime;

import java.util.Set;

/**
 * Created on 10/22/2015.
 */
public class InventoryDto extends AbstractDto {
    private StoreDto store;
    private FilmDto film;
    private Set<RentalDto> rentals;

    public InventoryDto() {
    }

    public InventoryDto(Builder builder) {
        setLastUpdated(builder.lastUpdated);
        setId(builder.id);
        setStore(builder.store);
        setFilm(builder.film);
        setRentals(builder.rentals);
    }

    public StoreDto getStore() {
        return store;
    }

    public void setStore(StoreDto store) {
        this.store = store;
    }

    public FilmDto getFilm() {
        return film;
    }

    public void setFilm(FilmDto film) {
        this.film = film;
    }

    public Set<RentalDto> getRentals() {
        return rentals;
    }

    public void setRentals(Set<RentalDto> rentals) {
        this.rentals = rentals;
    }

    public static class Builder {
        private int id;
        private DateTime lastUpdated;
        private StoreDto store;
        private FilmDto film;
        private Set<RentalDto> rentals;

        public Builder store(StoreDto store) {
            this.store = store;
            return this;
        }

        public Builder film(FilmDto film) {
            this.film = film;
            return this;
        }

        public Builder rentals(Set<RentalDto> rentals) {
            this.rentals = rentals;
            return this;
        }

        public Builder id(int storeId) {
            this.id = storeId;
            return this;
        }

        public Builder lastUpdated(DateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        public InventoryDto build() {
            return new InventoryDto(this);
        }
    }
}
