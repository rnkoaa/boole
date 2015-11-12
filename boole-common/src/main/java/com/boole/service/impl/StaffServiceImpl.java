package com.sakila.service.impl;

import com.sakila.domain.*;
import com.sakila.repository.StaffRepository;
import com.sakila.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 10/23/15.
 */
@Service
public class StaffServiceImpl extends BaseServiceImpl<Staff> implements StaffService {

    private final StaffRepository staffRepository;

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public Optional<Staff> find(Integer id) {
        Assert.notNull(id, "id cannot be null.");
        Staff staff = staffRepository.findOne(id);
        return Optional.ofNullable(staff);
    }

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public Staff save(Staff staff) {
        Assert.notNull(staff, "Staff cannot be null.");
        return staffRepository.save(staff);
    }

    @Override
    public Staff update(Staff staff) {
        Assert.notNull(staff, "Staff cannot be null.");
        return staffRepository.save(staff);
    }

    @Override
    public void delete(Integer id) {
        Assert.notNull(id, "Id cannot be null.");
        staffRepository.delete(id);
    }

    @Override
    public Page<Staff> findAll(Pageable pageable) {
        return staffRepository.findAll(pageable);
    }

    @Override
    public Optional<Staff> findByEmail(String email) {
        Staff staff = staffRepository.findByEmail(email);
        return Optional.ofNullable(staff);
    }

    @Override
    public Optional<Staff> findByUsername(String username) {
        Staff staff = staffRepository.findByUsername(username);
        return Optional.ofNullable(staff);
    }

    @Override
    public Optional<Store> findStoreForStaff(Integer id) {
        Store store = staffRepository.findStoreByStaffId(id);
        return Optional.ofNullable(store);
    }

    @Override
    public Set<Payment> findPaymentsByStaffId(Integer id) {
        Assert.notNull(id, "Id cannot be null.");
        return staffRepository.findPaymentsByStaffId(id);
    }

    @Override
    public Set<Rental> findRentalsByStaffId(Integer id) {
        Assert.notNull(id, "Id cannot be null.");
        return staffRepository.findRentalsByStaffId(id);
    }

    @Override
    public Optional<Address> findAddressForStaff(Integer id) {
        Assert.notNull(id, "Id cannot be null.");
        Address address = staffRepository.findAddressByStaffId(id);
        return Optional.ofNullable(address);
    }
}
