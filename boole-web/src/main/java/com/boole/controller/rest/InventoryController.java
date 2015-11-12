package com.boole.controller.rest;

import com.boole.controller.components.InventoryBuilderService;
import com.boole.controller.rest.dto.*;
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
@RequestMapping("/api/inventories")
public class InventoryController extends AbstractRestController {

    @Autowired
    private InventoryBuilderService inventoryBuilderService;

    //all
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<List<InventoryDto>> findAll(@RequestParam Map<String, String> requestParams) {
        return inventoryBuilderService.find(requestParams, createPageable(requestParams, null));
    }


    //id with options
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<InventoryDto> findAll(@PathVariable("id") Integer id,
                                              @RequestParam Map<String, String> requestParams) {
        InventoryDto inventoryDto = inventoryBuilderService.find(id, requestParams);
        return new RestResponse<>(inventoryDto);
    }

    //id with films
    @RequestMapping(value = "/{id}/film", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<FilmDto> findFilmsForInventory(@PathVariable("id") Integer id) {
        FilmDto filmDtos = inventoryBuilderService.findFilmByInventoryId(id);
        return new RestResponse<>(filmDtos);
    }

    //id with rentals
    @RequestMapping(value = "/{id}/rentals", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<Set<RentalDto>> findRentalsForInventory(@PathVariable("id") Integer id) {
        Set<RentalDto> rentals = inventoryBuilderService.findRentals(id);
        return new RestResponse<>(rentals, rentals.size(), 0, 0, null, null);
    }

    //id with store
    @RequestMapping(value = "/{id}/store", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<StoreDto> findStoreForInventory(@PathVariable("id") Integer id) {
        StoreDto store = inventoryBuilderService.findStore(id);
        return new RestResponse<>(store);
    }

    //id with all details
    @RequestMapping(value = "/{id}/details", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<InventoryDto> findInventoryDetails(@PathVariable("id") Integer id) {
        Map<String, String> requestParams = new HashMap<>(1);
        requestParams.put("include", "all");
        InventoryDto inventoryDto = inventoryBuilderService.find(id, requestParams);
        return new RestResponse<>(inventoryDto);
    }
}
