package com.boole.controller.components;

import com.boole.controller.rest.dto.*;
import com.boole.common.util.exceptions.NotFoundException;
import com.boole.domain.Address;
import com.boole.domain.Customer;
import com.boole.domain.Payment;
import com.boole.domain.Rental;
import com.boole.common.service.CustomerService;
import com.boole.common.util.StringUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 10/23/15.
 */
@Service
public class CustomerBuilderService extends BaseBuilderService<CustomerDto> {
    private static final String INCLUDE_KEY = "include";

    @Autowired
    private CustomerService customerService;

    public List<CustomerDto> find(Map<String, String> requestParams) {
        List<Customer> customers = customerService.findAll();
        List<CustomerDto> results = new ArrayList<>(0);
        if (!requestParams.containsKey(INCLUDE_KEY)) {
            results = customersToDto(customers);
        } else {
            String includes = requestParams.get(INCLUDE_KEY);

            String[] params = new String[]{};
            if (!StringUtil.isNullOrEmpty(includes)) {
                params = includes.split(",");
            }
            if (params.length > 0) {
                List<String> paramValues = Collections.unmodifiableList(Arrays.asList(params));
                if (paramValues.contains("address") || paramValues.contains("addresses")) {
                    results =
                            customers
                                    .stream()
                                    .map(this::customerToDtoWithAddress)
                                    .collect(Collectors.toList());
                }

            }
        }
        return results;
    }

    @Override
    public RestResponse<List<CustomerDto>> find(Map<String, String> requestParams, Pageable pageable) {
        Page<Customer> customerPage = customerService.findAll(pageable);
        List<Customer> customers = customerPage.getContent();
        List<CustomerDto> results = new ArrayList<>(0);
        if (requestParams.containsKey(INCLUDE_KEY)) {
            String includes = requestParams.get(INCLUDE_KEY);

            String[] params = new String[]{};
            if (!StringUtil.isNullOrEmpty(includes)) {
                params = includes.split(",");
            }
            if (params.length > 0) {
                List<String> paramValues = Collections.unmodifiableList(Arrays.asList(params));
                if (paramValues.contains("address") || paramValues.contains("addresses")) {
                    results =
                            customers
                                    .stream()
                                    .map(this::customerToDtoWithAddress)
                                    .collect(Collectors.toList());
                }

            }
        } else {
            results = customersToDto(customers);
        }

        ResponseMetadata responseMetadata = new ResponseMetadata(customerPage.getNumberOfElements(),
                customerPage.getTotalElements(),
                customerPage.getTotalPages(), customerPage.getNumber());

        return new RestResponse<>(results, responseMetadata);
    }

    public Set<RentalDto> findRentals(Integer id) {
        Set<Rental> rentals = customerService.findCustomerRentals(id);
        return rentals.stream()
                .map(this::toRentalDto)
                .collect(Collectors.toSet());
    }


    public Set<PaymentDto> findPayments(Integer id) {
        Set<Payment> payments = customerService.findCustomerPayments(id);
        return paymentsToDto(payments);
    }

    public CustomerDto find(Integer id, Map<String, String> requestParams) {
        Optional<Customer> optionalCustomer = customerService.find(id);
        Customer customer = optionalCustomer
                .orElseThrow(() -> new NotFoundException("Customer with Id: " + id + " was not found"));
        CustomerDto customerDto = null;
        if (!requestParams.containsKey(INCLUDE_KEY)) {
            customerDto = customerToDto(customer);
        } else {
            String includes = requestParams.get(INCLUDE_KEY);

            String[] params = new String[]{};
            if (!StringUtil.isNullOrEmpty(includes)) {
                params = includes.split(",");
            }
            if (params.length > 0) {
                List<String> paramValues = Collections.unmodifiableList(Arrays.asList(params));
                if (paramValues.contains("all")) {
                    customer.getAddress();
                    customer.getRentals();
                    customer.getPayments();
                    customerDto = customerToDto(customer, true, true, true);
                } else {
                    if (paramValues.contains("address")) {
                        customerDto = customerToDto(customer, true, false, false);
                    }
                    if (paramValues.contains("rentals")) {
                        customerDto = customerToDto(customer, false, true, false);
                    }
                    if (paramValues.contains("payments")) {
                        customerDto = customerToDto(customer, false, false, true);
                    }
                }
            }
        }
        return customerDto;
    }

    private CustomerDto customerToDto(Customer customer, boolean hasAddress,
                                      boolean hasRentals, boolean hasPayments) {
        CustomerDto.Builder builder = new CustomerDto.Builder();
        builder.active(customer.getActive())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .createdDate(new DateTime(customer.getCreateDate().getTime()))
                .lastUpdated(new DateTime(customer.getLastUpdated().getTime()))
                .id(customer.getCustomerId())
                .email(customer.getEmail());

        if (hasAddress) {
            Address address = customer.getAddress();
            builder.addressDto(fromAddress(address));
        }

        if (hasPayments) {
            Set<PaymentDto> paymentDtos = customer.getPayments().stream()
                    .map(this::paymentToDto)
                    .collect(Collectors.toSet());
            builder.paymentDtos(paymentDtos);
        }

        if (hasRentals) {
            Set<RentalDto> rentalDtos = customer.getRentals().stream()
                    .map(this::toRentalDto)
                    .collect(Collectors.toSet());
            builder.rentalDtos(rentalDtos);
        }
        return builder.build();
    }


    private AddressDto fromAddress(Address address) {
        AddressDto.Builder addressBuilder = new AddressDto.Builder();
        addressBuilder.id(address.getAddressId())
                .address(address.getAddress())
                .address2(address.getAddress2())
                .district(address.getDistrict())
                .city(address.getCity())
                .postalCode(address.getPostalCode());
        return addressBuilder.build();
    }

    private CustomerDto customerToDtoWithAddress(Customer customer) {
        CustomerDto.Builder builder = new CustomerDto.Builder();
        builder.active(customer.getActive())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .createdDate(new DateTime(customer.getCreateDate().getTime()))
                .lastUpdated(new DateTime(customer.getLastUpdated().getTime()))
                .id(customer.getCustomerId())
                .email(customer.getEmail());

        if (customer.getAddress() != null) {
            Address address = customer.getAddress();
            builder.addressDto(fromAddress(address));
        }
        return builder.build();
    }

    private RentalDto toRentalDto(Rental rental) {
        RentalDto.Builder builder = new RentalDto.Builder();
        builder.id(rental.getRentalId())
                .rentalDate(new DateTime(rental.getRentalDate().getTime()))
                .lastUpdated(new DateTime(rental.getLastUpdated().getTime()));
        if (rental.getReturnDate() != null)
            builder.rentalDate(new DateTime(rental.getReturnDate().getTime()));
        return builder.build();
    }


}
