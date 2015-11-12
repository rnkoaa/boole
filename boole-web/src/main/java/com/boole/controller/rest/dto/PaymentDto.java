package com.boole.controller.rest.dto;

import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * Created on 10/22/2015.
 */
public class PaymentDto extends AbstractDto {
    private BigDecimal amount;
    private DateTime paymentDate;

    public PaymentDto() {
    }

    public PaymentDto(Builder builder) {
        setId(builder.id);
        setLastUpdated(builder.lastUpdated);
        setAmount(builder.amount);
        setPaymentDate(builder.paymentDate);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public DateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(DateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public static class Builder {
        public DateTime lastUpdated;
        private int id;
        private BigDecimal amount;
        private DateTime paymentDate;

        public Builder lastUpdated(DateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder paymentDate(DateTime paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public PaymentDto build() {
            return new PaymentDto(this);
        }
    }
}
