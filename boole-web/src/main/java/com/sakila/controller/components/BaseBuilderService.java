package com.sakila.controller.components;

import com.sakila.controller.rest.dto.*;
import com.sakila.domain.*;
import org.joda.time.DateTime;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 10/23/15.
 */
public abstract class BaseBuilderService<T> {
    protected static final String INCLUDE_KEY = "include";

    protected FilmDto filmToDto(Film film) {
        return new FilmDto.Builder()
                .id(film.getFilmId())
                .title(film.getTitle())
                .description(film.getDescription())
                .releaseYear(film.getReleaseYear())
                .rating(film.getRating())
                .specialFeatures(film.getSpecialFeatures())
                .languages(film.getLanguage2(), film.getLanguage1())
                .build();
    }

    protected InventoryDto inventoryToDtoWithDetails(Inventory inventory) {
        InventoryDto inventoryDto = inventoryToDto(inventory);
        inventoryDto.setStore(storeToDto(inventory.getStore()));
        inventoryDto.setFilm(filmToDto(inventory.getFilm()));
        inventoryDto.setRentals(rentalsToDto(inventory.getRentals()));
        return inventoryDto;
    }

    protected AddressDto addressToDto(Address address) {
        AddressDto.Builder addressBuilder = new AddressDto.Builder();
        addressBuilder.id(address.getAddressId())
                .address(address.getAddress())
                .address2(address.getAddress2())
                .district(address.getDistrict())
                .city(address.getCity())
                .postalCode(address.getPostalCode());
        return addressBuilder.build();
    }

    protected InventoryDto inventoryToDtoWithStore(Inventory inventory) {
        InventoryDto inventoryDto = inventoryToDto(inventory);
        inventoryDto.setStore(storeToDto(inventory.getStore()));
        return inventoryDto;
    }

    protected InventoryDto inventoryToDtoWithRentals(Inventory inventory) {
        InventoryDto inventoryDto = inventoryToDto(inventory);
        inventoryDto.setRentals(rentalsToDto(inventory.getRentals()));
        return inventoryDto;
    }

    protected Set<RentalDto> rentalsToDto(Set<Rental> rentals) {
        return rentals.stream()
                .map(this::rentalToDto)
                .collect(Collectors.toSet());
    }

    protected RentalDto rentalToDto(Rental rental) {
        RentalDto.Builder builder = new RentalDto.Builder();
        builder.id(rental.getRentalId())
                .rentalDate(new DateTime(rental.getRentalDate().getTime()))
                .lastUpdated(new DateTime(rental.getLastUpdated().getTime()));
        if (rental.getReturnDate() != null)
            builder.rentalDate(new DateTime(rental.getReturnDate().getTime()));
        return builder.build();
    }

    protected InventoryDto inventoryToDtoWithFilm(Inventory inventory) {
        InventoryDto inventoryDto = inventoryToDto(inventory);
        inventoryDto.setFilm(filmToDto(inventory.getFilm()));
        return inventoryDto;
    }

    protected StoreDto storeToDto(Store store) {
        StoreDto.Builder builder = new StoreDto.Builder();
        builder.lastUpdated(new DateTime(store.getLastUpdated()))
                .id(store.getStoreId())
                .address(addressToDto(store.getAddress()))
                .manager(staffToDto(store.getStaff()));
        return builder.build();
    }

    protected StaffDto staffToDto(Staff manager) {
        StaffDto.Builder builder = new StaffDto.Builder();
        builder.active(manager.getActive())
                .firstName(manager.getFirstName())
                .lastName(manager.getLastName())
                .username(manager.getUsername())
                .password(manager.getPassword())
                .addressDto(addressToDto(manager.getAddress()))
                .lastUpdated(new DateTime(manager.getLastUpdated().getTime()))
                .id(manager.getStaffId())
                .email(manager.getEmail());

        return builder.build();
    }

    protected InventoryDto inventoryToDto(Inventory inventory) {
        InventoryDto.Builder builder = new InventoryDto.Builder();
        builder.id(inventory.getInventoryId())
                .lastUpdated(new DateTime(inventory.getLastUpdated()));
        return builder.build();
    }


    protected CustomerDto customerToDto(Customer customer) {
        CustomerDto.Builder builder = new CustomerDto.Builder();
        builder.active(customer.getActive())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .createdDate(new DateTime(customer.getCreateDate().getTime()))
                .lastUpdated(new DateTime(customer.getLastUpdated().getTime()))
                .id(customer.getCustomerId())
                .email(customer.getEmail());

        return builder.build();
    }

    protected List<CustomerDto> customersToDto(List<Customer> customers) {
        List<CustomerDto> results;
        results = customers
                .stream()
                .map(this::customerToDto)
                .collect(Collectors.toList());
        return results;
    }


    protected StoreDto storeToDtoWithCustomers(Store store) {
        StoreDto storeDto = storeToDto(store);
        storeDto.setCustomers(store.getCustomers()
                .stream()
                .map(this::customerToDto)
                .collect(Collectors.toSet()));

        return storeDto;
    }


    protected StaffDto staffToDtoWithDetails(Staff staff) {
        StaffDto staffDto = staffToDto(staff);
        staffDto.setRentals(rentalsToDto(staff.getRentals()));
        staffDto.setStore(storeToDto(staff.getStore()));
        staffDto.setPayments(paymentsToDto(staff.getPayments()));
        staffDto.setAddress(addressToDto(staff.getAddress()));
        return staffDto;
    }

    protected Set<PaymentDto> paymentsToDto(Set<Payment> payments) {
        return payments.stream()
                .map(this::paymentToDto)
                .collect(Collectors.toSet());
    }


    public PaymentDto paymentToDto(Payment payment) {
        PaymentDto.Builder paymentBuilder = new PaymentDto.Builder();
        paymentBuilder.id(payment.getPaymentId())
                .amount(payment.getAmount())
                .lastUpdated(new DateTime(payment.getLastUpdated().getTime()));

        if (payment.getPaymentDate() != null) {
            paymentBuilder.paymentDate(new DateTime(payment.getPaymentDate()));
        }
        return paymentBuilder.build();
    }

    public StaffDto staffDtoWithPayments(Staff staff) {
        StaffDto staffDto = staffToDto(staff);
        staffDto.setPayments(paymentsToDto(staff.getPayments()));
        return staffDto;
    }

    public StaffDto staffToDtoWithStore(Staff staff) {
        StaffDto staffDto = staffToDto(staff);
        staffDto.setStore(storeToDto(staff.getStore()));
        return staffDto;
    }

    public StaffDto staffToDtoWithAddress(Staff staff) {
        StaffDto staffDto = staffToDto(staff);
        staffDto.setAddress(addressToDto(staff.getAddress()));
        return staffDto;
    }

    public StaffDto staffToDtoWithRentals(Staff staff) {
        StaffDto staffDto = staffToDto(staff);
        staffDto.setRentals(rentalsToDto(staff.getRentals()));
        return staffDto;
    }


    public StoreDto storeToDtoWithDetails(Store store) {
        StoreDto storeDto = storeToDto(store);
        storeDto.setAddress(addressToDto(store.getAddress()));
        storeDto.setManager(staffToDto(store.getStaff()));
        storeDto.setStaffs(store.getStaffs()
                .stream()
                .map(this::staffToDto)
                .collect(Collectors.toSet()));
        storeDto.setCustomers(store.getCustomers()
                .stream()
                .map(this::customerToDto)
                .collect(Collectors.toSet()));

        storeDto.setInventories(store.getInventories()
                .stream()
                .map(this::inventoryToDto)
                .collect(Collectors.toSet()));
        return storeDto;
    }

    public StoreDto storeToDtoWithInventories(Store store) {
        StoreDto storeDto = storeToDto(store);
        storeDto.setInventories(store.getInventories()
                .stream()
                .map(this::inventoryToDto)
                .collect(Collectors.toSet()));

        return storeDto;
    }

    public StoreDto storeToDtoWithStaff(Store store) {
        StoreDto storeDto = storeToDto(store);
        storeDto.setStaffs(store.getStaffs()
                .stream()
                .map(this::staffToDto)
                .collect(Collectors.toSet()));

        return storeDto;
    }

    public StoreDto storeToDtoWithManager(Store store) {
        StoreDto storeDto = storeToDto(store);
        storeDto.setManager(staffToDto(store.getStaff()));
        return storeDto;
    }

    public StoreDto storeToDtoWithAddress(Store store) {
        StoreDto storeDto = storeToDto(store);
        storeDto.setAddress(addressToDto(store.getAddress()));
        return storeDto;
    }

    public Set<String> categoriesToDto(Set<Category> categories) {
        return categories.stream()
                .map(Category::getName)
                .collect(Collectors.toSet());
    }

    public Set<ActorDto> actorToDtos(Set<Actor> actors) {
        return actors.stream()
                .map(actor -> new ActorDto.Builder().id(actor.getActorId())
                        .firstName(actor.getFirstName())
                        .lastName(actor.getLastName())
                        .lastUpdated(new DateTime(actor.getLastUpdated().getTime()))
                        .build())
                .collect(Collectors.toSet());
    }

    public abstract RestResponse<List<T>> find(Map<String, String> requestParams, Pageable pageable);
}
