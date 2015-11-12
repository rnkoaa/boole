package com.sakila.controller.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;

import java.util.Set;

/**
 * Created on 10/22/2015.
 */
public class StaffDto extends PersonDto {
    private byte active;
    private String password;
    private byte[] picture;
    private String username;
    private Set<PaymentDto> payments;
    private Set<RentalDto> rentals;
    private StoreDto store;
    private AddressDto address;
    private Set<StoreDto> stores;

    public StaffDto() {
    }

    public StaffDto(Builder builder) {
        setId(builder.id);
        setLastUpdated(builder.lastUpdated);
        setFirstName(builder.firstName);
        setLastName(builder.lastName);
        setEmail(builder.email);
        setPassword(builder.password);
        setPicture(builder.picture);
        setUsername(builder.username);
        setPayments(builder.payments);
        setRentals(builder.rentals);
        setStore(builder.store);
        setStores(builder.stores);
        setAddress(builder.address);
    }

    public byte getActive() {
        return active;
    }

    public void setActive(byte active) {
        this.active = active;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<PaymentDto> getPayments() {
        return payments;
    }

    public void setPayments(Set<PaymentDto> payments) {
        this.payments = payments;
    }

    public Set<RentalDto> getRentals() {
        return rentals;
    }

    public void setRentals(Set<RentalDto> rentals) {
        this.rentals = rentals;
    }

    public StoreDto getStore() {
        return store;
    }

    public void setStore(StoreDto store) {
        this.store = store;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public Set<StoreDto> getStores() {
        return stores;
    }

    public void setStores(Set<StoreDto> stores) {
        this.stores = stores;
    }

    public static class Builder {
        public byte active;
        private int id;
        private DateTime lastUpdated;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private byte[] picture;
        private String username;
        private Set<PaymentDto> payments;
        private Set<RentalDto> rentals;
        private StoreDto store;
        private AddressDto address;
        private Set<StoreDto> stores;

        public Builder id(int storeId) {
            this.id = storeId;
            return this;
        }

        public Builder lastUpdated(DateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        public Builder picture(byte[] picture) {
            this.picture = picture;
            return this;
        }

        public Builder active(byte active) {
            this.active = active;
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

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder addressDto(AddressDto address) {
            this.address = address;
            return this;
        }

        public Builder rentalDtos(Set<RentalDto> rentals) {
            this.rentals = rentals;
            return this;
        }

        public Builder paymentDtos(Set<PaymentDto> payments) {
            this.payments = payments;
            return this;
        }

        public Builder storeDtos(Set<StoreDto> stores) {
            this.stores = stores;
            return this;
        }

        public Builder storeDto(StoreDto store) {
            this.store = store;
            return this;
        }

        public StaffDto build(){
            return new StaffDto(this);
        }
    }
}
