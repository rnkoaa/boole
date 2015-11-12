package com.sakila.controller.rest.dto;

import org.joda.time.DateTime;

import java.util.Set;

/**
 * Created on 10/22/2015.
 */
public class StoreDto extends AbstractDto {
    private Set<CustomerDto> customers;
    private Set<InventoryDto> inventories;
    private Set<StaffDto> staffs;
    private StaffDto manager;
    private AddressDto address;

    public StoreDto() {
    }

    public StoreDto(Builder builder) {
        setLastUpdated(builder.lastUpdated);
        setId(builder.id);
        setCustomers(builder.customers);
        setInventories(builder.inventories);
        setStaffs(builder.staffs);
        setManager(builder.manager);
        setAddress(builder.address);
    }

    public Set<CustomerDto> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<CustomerDto> customers) {
        this.customers = customers;
    }

    public Set<InventoryDto> getInventories() {
        return inventories;
    }

    public void setInventories(Set<InventoryDto> inventories) {
        this.inventories = inventories;
    }

    public Set<StaffDto> getStaffs() {
        return staffs;
    }

    public void setStaffs(Set<StaffDto> staffs) {
        this.staffs = staffs;
    }

    public StaffDto getManager() {
        return manager;
    }

    public void setManager(StaffDto manager) {
        this.manager = manager;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public static class Builder {

        private Set<CustomerDto> customers;
        private Set<InventoryDto> inventories;
        private Set<StaffDto> staffs;
        private StaffDto manager;
        private AddressDto address;
        private int id;
        private DateTime lastUpdated;

        public Builder id(int storeId) {
            this.id = storeId;
            return this;
        }

        public Builder lastUpdated(DateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        public Builder customers(Set<CustomerDto> customers) {
            this.customers = customers;
            return this;
        }

        public Builder inventories(Set<InventoryDto> inventories) {
            this.inventories = inventories;
            return this;
        }

        public Builder staffs(Set<StaffDto> staffs) {
            this.staffs = staffs;
            return this;
        }

        public Builder manager(StaffDto manager) {
            this.manager = manager;
            return this;
        }

        public Builder address(AddressDto address) {
            this.address = address;
            return this;
        }

        public StoreDto build() {
            return new StoreDto(this);
        }

    }
}
