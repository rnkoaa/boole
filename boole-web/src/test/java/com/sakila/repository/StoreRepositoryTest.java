package com.boole.repository;

import com.boole.config.booleApplication;
import com.boole.domain.Customer;
import com.boole.domain.Store;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 10/29/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = booleApplication.class)
public class StoreRepositoryTest {
    @Autowired
    public StoreRepository storeRepository;

    @Autowired
    public CustomerRepository customerRepository;

    @Test
    public void findRepositoryStoreCustomers() {
        Set<Customer> customers = storeRepository.findCustomersByStoreId(1);
        assertThat(customers).isNotNull();
        assertThat(customers.size()).isGreaterThan(0);
    }

    @Test
    public void findCustomersByStoreId() {
        Store store = new Store();
        store.setStoreId(2);
        Set<Customer> customerPage = customerRepository.findByStore(store);
        System.out.println("Customers returned: " + customerPage.size());
        assertThat(customerPage.size()).isGreaterThan(1);
    }


    @Test
    public void findCustomersByStoreIdPaged() {
        org.springframework.data.domain.Pageable page = new PageRequest(0, 20);
        Store store = new Store();
        store.setStoreId(2);
        Page<Customer> customerPage = customerRepository.findByStore(store, page);
        System.out.println("Customers returned: " + customerPage.getContent().size());
        assertThat(customerPage.getTotalElements()).isGreaterThan(1);
        assertThat(customerPage.getNumber()).isEqualTo(0);
        assertThat(customerPage.getTotalPages()).isGreaterThan(1);
    }
}
