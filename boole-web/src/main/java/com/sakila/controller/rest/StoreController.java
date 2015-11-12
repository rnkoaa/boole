package com.sakila.controller.rest;

import com.sakila.controller.components.StoreBuilderService;
import com.sakila.controller.rest.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created on 10/21/2015.
 */
@RestController
@RequestMapping("/api/stores")
public class StoreController extends AbstractRestController {

    @Autowired
    private StoreBuilderService storeBuilderService;

    //stores
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<List<StoreDto>> findAll(@RequestParam Map<String, String> requestParams) {
        return storeBuilderService.find(requestParams, createPageable(requestParams, null));
    }

    //store with id
    //options, include customers,inventories,staffs,manager,address
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<StoreDto> findStoreById(@PathVariable("id") Integer id,
                                                @RequestParam Map<String, String> requestParams) {
        return new RestResponse<>(storeBuilderService.find(id, requestParams));
    }

    //store customers
    @RequestMapping(value = "/{id}/customers", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<List<CustomerDto>> findCustomersByStoreId(@PathVariable("id") Integer id,
                                                                  @RequestParam Map<String, String> requestParams) {
        RestResponse<List<CustomerDto>> results;
        if (requestParams.containsKey("per_page") || requestParams.containsKey("page"))
            results = storeBuilderService.findCustomersByStoreId(id, createPageable(requestParams, null));
        else {
            results = storeBuilderService.findCustomersByStoreId(id);
        }
        return results;
    }

    //store inventories
    @RequestMapping(value = "/{id}/inventories", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<Set<InventoryDto>> findInventoriesByStoreId(@PathVariable("id") Integer id) {
        Set<InventoryDto> inventories = storeBuilderService.findInventoriesByStoreId(id);
        return new RestResponse<>(inventories, inventories.size(), 0, 0, null, null);
    }

    //store staffs
    @RequestMapping(value = "/{id}/staff", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<Set<StaffDto>> findStaffsByStoreId(@PathVariable("id") Integer id) {
        Set<StaffDto> staffs = storeBuilderService.findStaffByStoreId(id);
        return new RestResponse<>(staffs, staffs.size(), 0, 0, null, null);
    }

    //store manager
    @RequestMapping(value = "/{id}/manager", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<StaffDto> findManagerByStoreId(@PathVariable("id") Integer id) {
        StaffDto staffDto = storeBuilderService.findStoreManager(id);
        return new RestResponse<>(staffDto);
    }

    //store address
    @RequestMapping(value = "/{id}/address", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<AddressDto> findAddressByStoreId(@PathVariable("id") Integer id) {
        AddressDto addressDto = storeBuilderService.findStoreAddress(id);
        return new RestResponse<>(addressDto);
    }

    //store details with id
    @RequestMapping(value = "/{id}/details", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<StoreDto> findStoreWithDetailsById(@PathVariable("id") Integer id) {
        Map<String, String> requestParams = new HashMap<>(1);
        requestParams.put("include", "details");
        StoreDto storeDto = storeBuilderService.find(id, requestParams);
        return new RestResponse<>(storeDto);
    }
}
