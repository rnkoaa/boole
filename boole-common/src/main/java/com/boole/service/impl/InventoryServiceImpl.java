package com.sakila.service.impl;

import com.sakila.domain.Film;
import com.sakila.domain.Inventory;
import com.sakila.domain.Rental;
import com.sakila.domain.Store;
import com.sakila.repository.InventoryRepository;
import com.sakila.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created on 10/23/2015.
 */
@Service
public class InventoryServiceImpl extends BaseServiceImpl<Inventory> implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Optional<Inventory> find(Integer id) {
        Inventory inventory = inventoryRepository.findOne(id);

        return Optional.ofNullable(inventory);
    }

    @Override
    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory save(Inventory inventory) {
        Assert.notNull(inventory, "Inventory object cannot be null while saving.");
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory update(Inventory inventory) {
        Assert.notNull(inventory, "Inventory object cannot be null while saving.");

        System.out.println("Should trigger");
        return inventoryRepository.save(inventory);
    }

    @Override
    public void delete(Integer id) {
        Assert.notNull(id, "id cannot be null while saving.");
        inventoryRepository.delete(id);
    }

    @Override
    public Page<Inventory> findAll(Pageable pageable) {
        return inventoryRepository.findAll(pageable);
    }

    @Override
    public Optional<Film> findFilmsById(Integer id) {
        Assert.notNull(id, "id cannot be null while saving.");
        Film film = inventoryRepository.findFilmById(id);

        return Optional.ofNullable(film);
    }

    @Override
    public Set<Rental> findRentalsById(Integer id) {
        Assert.notNull(id, "id cannot be null while saving.");
        return inventoryRepository.findRentalsById(id);
    }

    @Override
    public Optional<Store> findStoreById(Integer id) {
        Assert.notNull(id, "id cannot be null while saving.");
        Store store = inventoryRepository.findStoreById(id);

        return Optional.ofNullable(store);
    }
}
