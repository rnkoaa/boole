package com.boole.controller.rest.dto;

import org.joda.time.DateTime;

import java.util.Set;

/**
 * Created on 10/22/2015.
 */
public class CustomerDto extends PersonDto {

    private DateTime createdDate;
    private AddressDto address;
    private Set<RentalDto> rentals;
    private Set<PaymentDto> payments;
    private boolean active;

    public CustomerDto() {
    }

    public CustomerDto(Builder builder) {
        setLastUpdated(builder.lastUpdated);
        setId(builder.id);
        setFirstName(builder.firstName);
        setLastName(builder.lastName);
        setAddress(builder.addressDto);
        setRentals(builder.rentalDtos);
        setPayments(builder.paymentDtos);
        setEmail(builder.email);
        setCreatedDate(builder.createdDate);
        setActive((int) builder.active > 0);
    }

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public Set<RentalDto> getRentals() {
        return rentals;
    }

    public void setRentals(Set<RentalDto> rentals) {
        this.rentals = rentals;
    }

    public Set<PaymentDto> getPayments() {
        return payments;
    }

    public void setPayments(Set<PaymentDto> payments) {
        this.payments = payments;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public static class Builder {
        public byte active;
        private int id;
        private String firstName;
        private String lastName;
        private String email;
        private DateTime lastUpdated;
        private DateTime createdDate;
        private AddressDto addressDto;
        private Set<RentalDto> rentalDtos;
        private Set<PaymentDto> paymentDtos;

        public Builder id(int id) {
            this.id = id;
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

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder lastUpdated(DateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        public Builder createdDate(DateTime createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder addressDto(AddressDto addressDto) {
            this.addressDto = addressDto;
            return this;
        }

        public Builder rentalDtos(Set<RentalDto> rentalDtos) {
            this.rentalDtos = rentalDtos;
            return this;
        }

        public Builder paymentDtos(Set<PaymentDto> paymentDtos) {
            this.paymentDtos = paymentDtos;
            return this;
        }

        public CustomerDto build() {
            return new CustomerDto(this);
        }


    }
}
