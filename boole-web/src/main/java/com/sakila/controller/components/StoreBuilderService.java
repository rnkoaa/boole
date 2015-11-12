package com.sakila.controller.components;

import com.sakila.controller.rest.dto.*;
import com.sakila.util.exceptions.NotFoundException;
import com.sakila.domain.Customer;
import com.sakila.domain.Inventory;
import com.sakila.domain.Staff;
import com.sakila.domain.Store;
import com.sakila.service.CustomerService;
import com.sakila.service.StoreService;
import com.sakila.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created on 10/23/2015.
 */
@Component
public class StoreBuilderService extends BaseBuilderService<StoreDto> {

    private final StoreService storeService;

    private final CustomerService customerService;

    @Autowired
    public StoreBuilderService(StoreService storeService, CustomerService customerService) {
        this.storeService = storeService;
        this.customerService = customerService;
    }

    public StoreDto find(Integer id, Map<String, String> requestParams) {
        Optional<Store> optionalStore = storeService.find(id);
        Store store = optionalStore
                .orElseThrow(() -> new NotFoundException("Store with Id: " + id + " was not found"));
        StoreDto storeDto = null;
        if (requestParams.size() <= 0) {
            storeDto = storeToDto(store);
        } else {
            String includes = requestParams.get(INCLUDE_KEY);

            String[] params = new String[]{};
            if (!StringUtil.isNullOrEmpty(includes)) {
                params = includes.split(",");
            }
            if (params.length > 0) {
                List<String> paramValues = Collections.unmodifiableList(Arrays.asList(params));
                if (paramValues.contains("all") || paramValues.contains("details")) {
                    storeDto = storeToDtoWithDetails(store);
                } else {
                    if (paramValues.contains("customers")) {
                        storeDto = storeToDtoWithCustomers(store);
                    }
                    if (paramValues.contains("inventories")) {
                        storeDto = storeToDtoWithInventories(store);
                    }
                    if (paramValues.contains("staffs")) {
                        storeDto = storeToDtoWithManager(store);
                    }
                    if (paramValues.contains("manager")) {
                        storeDto = storeToDtoWithManager(store);
                    }
                    if (paramValues.contains("address")) {
                        storeDto = storeToDtoWithAddress(store);
                    }
                }
            }
        }
        return storeDto;
    }


    @Override
    public RestResponse<List<StoreDto>> find(Map<String, String> requestParams, Pageable pageable) {
        Page<Store> storePage = storeService.findAll(pageable);
        List<StoreDto> results = findStores(requestParams, storePage.getContent());


        ResponseMetadata responseMetadata = new ResponseMetadata(storePage.getNumberOfElements(),
                storePage.getTotalElements(),
                storePage.getTotalPages(), storePage.getNumber());

        return new RestResponse<>(results, responseMetadata);
    }

    public AddressDto findStoreAddress(Integer id) {
        return addressToDto(storeService
                .findAddressById(id)
                .orElseThrow(() ->
                        new NotFoundException("We could not find an address associated with store id: " + id)));
    }

    public StaffDto findStoreManager(Integer id) {
        return staffToDto(storeService
                .findManagerById(id)
                .orElseThrow(() ->
                        new NotFoundException("We could not find a manager associated with store id: " + id)));
    }

    public List<StoreDto> find(Map<String, String> requestParams) {
        List<Store> stores = storeService.findAll();
        return findStores(requestParams, stores);
    }

    protected List<StoreDto> findStores(Map<String, String> requestParams, List<Store> stores) {
        List<StoreDto> results = new ArrayList<>(0);
        if (!requestParams.containsKey(INCLUDE_KEY)) {
            results = stores
                    .stream()
                    .map(this::storeToDto)
                    .collect(Collectors.toList());
        } else {
            String includes = requestParams.get(INCLUDE_KEY);

            String[] params = new String[]{};
            if (!StringUtil.isNullOrEmpty(includes)) {
                params = includes.split(",");
            }
            if (params.length > 0) {
                List<String> paramValues = Collections.unmodifiableList(Arrays.asList(params));
                if (paramValues.contains("all") || paramValues.contains("details")) {
                    results =
                            stores
                                    .stream()
                                    .map(this::storeToDtoWithDetails)
                                    .collect(Collectors.toList());
                }
                if (paramValues.contains("customers")) {
                    results =
                            stores
                                    .stream()
                                    .map(this::storeToDtoWithCustomers)
                                    .collect(Collectors.toList());
                }

                if (paramValues.contains("inventories") || paramValues.contains("inventory")) {
                    results =
                            stores
                                    .stream()
                                    .map(this::storeToDtoWithInventories)
                                    .collect(Collectors.toList());
                }

                if (paramValues.contains("staff")) {
                    results =
                            stores
                                    .stream()
                                    .map(this::storeToDtoWithStaff)
                                    .collect(Collectors.toList());
                }
                if (paramValues.contains("manager")) {
                    results =
                            stores
                                    .stream()
                                    .map(this::storeToDtoWithManager)
                                    .collect(Collectors.toList());
                }

                if (paramValues.contains("address")) {
                    results =
                            stores
                                    .stream()
                                    .map(this::storeToDtoWithAddress)
                                    .collect(Collectors.toList());
                }

            }
        }
        return results;
    }

    public RestResponse<List<CustomerDto>> findCustomersByStoreId(Integer id) {
        Set<Customer> customers = storeService.findCustomersByStoreId(id);
        List<CustomerDto> customerDtos = customersToDto(customers.stream().collect(Collectors.toList()));

        ResponseMetadata responseMetadata = new ResponseMetadata(customers.size(),
                customers.size(),
                1, 0);
        return new RestResponse<>(customerDtos, responseMetadata);
    }

    public Set<InventoryDto> findInventoriesByStoreId(Integer id) {
        Set<Inventory> inventories = storeService.findInventoriesByStoreId(id);
        return inventories.stream()
                .map(this::inventoryToDto)
                .collect(Collectors.toSet());
    }

    public Set<StaffDto> findStaffByStoreId(Integer id) {
        Set<Staff> staffs = storeService.findStaffByStoreId(id);
        return staffs.stream()
                .map(this::staffToDto)
                .collect(Collectors.toSet());
    }

    public RestResponse<List<CustomerDto>> findCustomersByStoreId(Integer id, Pageable pageable) {
        Page<Customer> customerPage = customerService.findStoreCustomers(id, pageable);

        List<CustomerDto> customerDtos = customersToDto(customerPage.getContent().stream().collect(Collectors.toList()));

        ResponseMetadata responseMetadata = new ResponseMetadata(customerPage.getNumberOfElements(),
                customerPage.getTotalElements(),
                customerPage.getTotalPages(), customerPage.getNumber());

        return new RestResponse<>(customerDtos, responseMetadata);
    }
}
