package com.sakila.service.impl;

import com.sakila.domain.*;
import com.sakila.repository.CustomerRepository;
import com.sakila.service.CustomerService;
import com.sakila.util.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 10/23/15.
 */
@Service
@Transactional(readOnly = true)
public class CustomerServiceImpl extends BaseServiceImpl<Customer> implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<Customer> find(Integer id) {
        Customer actor = customerRepository.findOne(id);

        return Optional.ofNullable(actor);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer save(Customer customer) {
        Assert.notNull(customer, "Customer object cannot be null while saving.");
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        Assert.notNull(customer, "Customer object cannot be null while saving.");
        return customerRepository.save(customer);
    }

    @Override
    public void delete(Integer id) {
        Assert.notNull(id, "id cannot be null while saving.");
        customerRepository.delete(id);
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Address findCustomerAddress(Integer id) {
        return customerRepository.findAddressByCustomerId(id);
    }

    @Override
    public Set<Payment> findCustomerPayments(Integer id) {
        return customerRepository.findPaymentsByCustomerId(id);
    }

    @Override
    public Set<Rental> findCustomerRentals(Integer id) {
        return customerRepository.findRentalsByCustomerId(id);
    }

    @Override
    public Address findCustomerAddress(String email) {
        return customerRepository.findAddressByEmail(email);
    }

    @Override
    public Set<Payment> findCustomerPayments(String email) {
        return customerRepository.findPaymentsByEmail(email);
    }

    @Override
    public Set<Rental> findCustomerRentals(String email) {
        return customerRepository.findRentalsByEmail(email);
    }

    @Override
    public boolean customerExists(String email) {
        return customerRepository.findByEmail(email) != null;
    }

    @Override
    public boolean activateCustomer(String email) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null)
            throw new NotFoundException("Cannot activate customer that does not exist");

        if ((int) customer.getActive() > 0)
            throw new IllegalArgumentException("Cannot activate a customer that is already active");

        customer.setActive((byte) 1);

        return customerRepository.save(customer) != null;
    }

    @Override
    public boolean deactivateCustomer(String email) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null)
            throw new NotFoundException("Cannot deactivate customer that does not exist");

        if ((int) customer.getActive() <= 0)
            throw new IllegalArgumentException("Cannot deactivate a customer that is already inactive");

        customer.setActive((byte) 0);

        return customerRepository.save(customer) != null;
    }

    @Override
    public Page<Customer> findStoreCustomers(Integer storeId, Pageable pageable) {
        Assert.notNull(storeId, "id cannot be null while saving.");
        Store store = new Store();
        store.setStoreId(storeId);
        return customerRepository.findByStore(store, pageable);
    }
}
