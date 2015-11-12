package com.sakila.controller.rest.dto;

import com.sakila.domain.City;
import com.sakila.domain.Country;
import org.joda.time.DateTime;

/**
 * Created on 10/22/2015.
 */
public class AddressDto extends AbstractDto {
    private String address;
    private String address2;
    private String district;
    private String phone;
    private String postalCode;
    private String city;
    private String country;

    public AddressDto() {
    }

    public AddressDto(Builder builder) {
        setLastUpdated(builder.lastUpdated);
        setId(builder.id);
        setAddress(builder.address);
        setAddress2(builder.address2);
        setDistrict(builder.district);
        setPhone(builder.phone);
        setPostalCode(builder.postalCode);
        setCity(builder.city);
        setCountry(builder.country);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static class Builder {
        public DateTime lastUpdated;
        private String address;
        private String address2;
        private String district;
        private String phone;
        private String postalCode;
        private String city;
        private String country;
        private int id;

        public Builder lastUpdated(DateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder address2(String address2) {
            this.address2 = address2;
            return this;
        }

        public Builder district(String district) {
            this.district = district;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder city(City city) {
            if (city != null)
                return city(city.getCity());
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder country(Country country) {
            if (country != null)
                return country(country.getCountry());
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public AddressDto build() {
            return new AddressDto(this);
        }
    }
}
