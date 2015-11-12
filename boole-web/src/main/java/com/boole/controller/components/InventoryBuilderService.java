package com.boole.controller.components;

import com.boole.controller.rest.dto.*;
import com.boole.common.util.exceptions.NotFoundException;
import com.boole.domain.Inventory;
import com.boole.domain.Rental;
import com.boole.common.service.InventoryService;
import com.boole.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created on 10/23/2015.
 */
@Component
@Transactional(readOnly = true)
public class InventoryBuilderService extends BaseBuilderService<InventoryDto> {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryBuilderService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @Transactional
    public List<InventoryDto> find(Map<String, String> requestParams) {
        List<Inventory> inventories = inventoryService.findAll();
        List<InventoryDto> results = new ArrayList<>(0);
        if (requestParams.size() <= 0) {
            results = inventories
                    .stream()
                    .map(this::inventoryToDto)
                    .collect(Collectors.toList());
        } else {
            String includes = requestParams.get(INCLUDE_KEY);

            String[] params = new String[]{};
            if (!StringUtil.isNullOrEmpty(includes)) {
                params = includes.split(",");
            }
            if (params.length > 0) {
                List<String> paramValues = Collections.unmodifiableList(Arrays.asList(params));
                results = processInventoryDto(paramValues, inventories);
            }
        }
        return results;
    }

    private List<InventoryDto> convertInventoriesWithDetails(List<Inventory> inventories) {
        return inventories
                .stream()
                .map(this::inventoryToDtoWithDetails)
                .collect(Collectors.toList());
    }

    private List<InventoryDto> convertInventoriesWithRentals(List<Inventory> inventories) {
        return inventories
                .stream()
                .map(this::inventoryToDtoWithRentals)
                .collect(Collectors.toList());
    }

    private List<InventoryDto> convertInventoriesWithStore(List<Inventory> inventories) {
        return inventories
                .stream()
                .map(this::inventoryToDtoWithStore)
                .collect(Collectors.toList());
    }

    private List<InventoryDto> convertInventoriesWithFilms(List<Inventory> inventories) {
        return inventories
                .stream()
                .map(this::inventoryToDtoWithFilm)
                .collect(Collectors.toList());
    }


    @Override
    public RestResponse<List<InventoryDto>> find(Map<String, String> requestParams, Pageable pageable) {
        Page<Inventory> inventoryPage = inventoryService.findAll(pageable);
        List<Inventory> inventories = inventoryPage.getContent();
        List<InventoryDto> results = new ArrayList<>(0);
        if (!requestParams.containsKey(INCLUDE_KEY)) {
            results = inventories
                    .stream()
                    .map(this::inventoryToDto)
                    .collect(Collectors.toList());
        } else {
            String includes = requestParams.get(INCLUDE_KEY);

            String[] params = new String[]{};
            if (!StringUtil.isNullOrEmpty(includes)) {
                params = includes.split(",");
            }
            if (params.length > 0) {
                List<String> paramValues = Collections.unmodifiableList(Arrays.asList(params));
                results = processInventoryDto(paramValues, inventories);
            }
        }

        ResponseMetadata responseMetadata = new ResponseMetadata(inventoryPage.getNumberOfElements(),
                inventoryPage.getTotalElements(),
                inventoryPage.getTotalPages(), inventoryPage.getNumber());

        return new RestResponse<>(results, responseMetadata);
    }

    private List<InventoryDto> processInventoryDto(List<String> paramValues, List<Inventory> inventories) {
        List<InventoryDto> results = new ArrayList<>();
        if (paramValues.contains("all") || paramValues.contains("details")) {
            results = convertInventoriesWithDetails(inventories);
        }
        if (paramValues.contains("store")) {
            results = convertInventoriesWithStore(inventories);
        }

        if (paramValues.contains("film") || paramValues.contains("films")) {
            results = convertInventoriesWithFilms(inventories);
        }

        if (paramValues.contains("rentals") || paramValues.contains("rental")) {
            results = convertInventoriesWithRentals(inventories);
        }
        return results;
    }


    public InventoryDto find(Integer id, Map<String, String> requestParams) {
        Optional<Inventory> optionalInventory = inventoryService.find(id);
        Inventory inventory = optionalInventory
                .orElseThrow(() ->
                        new NotFoundException("Inventory with Id: " + id + " Was not found"));

        InventoryDto inventoryDto = null;
        if (requestParams.size() > 0) {
            String includes = requestParams.get(INCLUDE_KEY);

            String[] params = new String[]{};
            if (!StringUtil.isNullOrEmpty(includes)) {
                params = includes.split(",");
            }
            if (params.length > 0) {
                List<String> paramValues = Collections.unmodifiableList(Arrays.asList(params));
                if (paramValues.contains("all") || paramValues.contains("details")) {
                    inventoryDto = inventoryToDtoWithDetails(inventory);
                }
                if (paramValues.contains("store")) {
                    inventoryDto = inventoryToDtoWithStore(inventory);
                }

                if (paramValues.contains("film") || paramValues.contains("films")) {
                    inventoryDto = inventoryToDtoWithFilm(inventory);
                }

                if (paramValues.contains("rentals") || paramValues.contains("rental")) {
                    inventoryDto = inventoryToDtoWithRentals(inventory);
                }
            }
        } else {
            inventoryDto = inventoryToDto(inventory);
        }
        return inventoryDto;
    }

    public FilmDto findFilmByInventoryId(Integer id) {
        return filmToDto(inventoryService
                .findFilmsById(id)
                .orElseThrow(() ->
                        new NotFoundException("We could not find a film associated with inventory id: " + id)));
    }

    public Set<RentalDto> findRentals(Integer id) {
        Set<Rental> rentals = inventoryService.findRentalsById(id);
        return rentals.stream()
                .map(this::rentalToDto)
                .collect(Collectors.toSet());
    }

    public StoreDto findStore(Integer id) {
        return storeToDto(inventoryService
                .findStoreById(id)
                .orElseThrow(() ->
                        new NotFoundException("We could not find a store associated with inventory id: " + id)));
    }

}
