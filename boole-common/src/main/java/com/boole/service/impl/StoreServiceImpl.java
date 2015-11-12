package com.sakila.service.impl;

import com.sakila.domain.*;
import com.sakila.repository.StoreRepository;
import com.sakila.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created on 10/21/2015.
 */
@Service
public class StoreServiceImpl extends BaseServiceImpl<Store> implements StoreService {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Optional<Store> find(Integer id) {
        Store store = storeRepository.findOne(id);

        return Optional.ofNullable(store);
    }

    @Override
    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    @Override
    public Store save(Store store) {
        Assert.notNull(store, "Store cannot be null while saving.");
        return storeRepository.save(store);
    }

    @Override
    public Store update(Store store) {
        Assert.notNull(store, "Store cannot be null while saving.");
        return storeRepository.save(store);
    }

    @Override
    public void delete(Integer id) {
        Assert.notNull(id, "id cannot be null.");
        storeRepository.delete(id);
    }

    @Override
    public Page<Store> findAll(Pageable pageable) {
        return storeRepository.findAll(pageable);
    }

    @Override
    public Optional<Address> findAddressById(Integer id) {
        Assert.notNull(id, "id cannot be null.");
        Address address = storeRepository.findAddressByStoreId(id);

        return Optional.ofNullable(address);
    }

    @Override
    public Optional<Staff> findManagerById(Integer id) {
        Assert.notNull(id, "id cannot be null.");
        Staff staff = storeRepository.findManagerByStoreId(id);

        return Optional.ofNullable(staff);
    }

    @Override
    public Set<Customer> findCustomersByStoreId(Integer id) {
        return storeRepository.findCustomersByStoreId(id);
    }

    @Override
    public Set<Inventory> findInventoriesByStoreId(Integer id) {
        return storeRepository.findInventoriesByStoreId(id);
    }

    @Override
    public Set<Staff> findStaffByStoreId(Integer id) {
        return storeRepository.findStaffByStoreId(id);
    }
}
