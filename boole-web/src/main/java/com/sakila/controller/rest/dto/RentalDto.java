package com.sakila.controller.rest.dto;

import org.joda.time.DateTime;

import java.util.Set;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 10/22/15.
 */
public class RentalDto extends AbstractDto {
    private DateTime rentalDate;
    private DateTime returnDate;
    private Set<PaymentDto> payments;

    public RentalDto() {
    }

    public RentalDto(Builder rentalDtoBuilder) {
        setId(rentalDtoBuilder.id);
        setLastUpdated(rentalDtoBuilder.lastUpdated);
        setRentalDate(rentalDtoBuilder.rentalDate);
        setReturnDate(rentalDtoBuilder.returnDate);
        setPayments(rentalDtoBuilder.payments);
    }

    public DateTime getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(DateTime rentalDate) {
        this.rentalDate = rentalDate;
    }

    public DateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(DateTime returnDate) {
        this.returnDate = returnDate;
    }

    public Set<PaymentDto> getPayments() {
        return payments;
    }

    public void setPayments(Set<PaymentDto> payments) {
        this.payments = payments;
    }

    public static class Builder {
        public DateTime lastUpdated;
        private int id;
        private DateTime rentalDate;
        private DateTime returnDate;
        private Set<PaymentDto> payments;

        public Builder rentalDate(DateTime rentalDate) {
            this.rentalDate = rentalDate;
            return this;
        }

        public Builder lastUpdated(DateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder returnDate(DateTime returnDate) {
            this.returnDate = returnDate;
            return this;
        }

        public Builder payments(Set<PaymentDto> payments) {
            this.payments = payments;
            return this;
        }

        public RentalDto build() {
            return new RentalDto(this);
        }
    }
}
