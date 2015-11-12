package com.boole.controller.rest;

import com.boole.controller.components.StaffBuilderService;
import com.boole.controller.rest.dto.*;
import com.boole.common.util.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created on 10/21/2015.
 */
@RestController
@RequestMapping("/api/staff")
public class StaffController extends AbstractRestController {

    @Autowired
    private StaffBuilderService staffBuilderService;
    //all

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<List<StaffDto>> findAll(@RequestParam Map<String, String> requestParams) {
        return staffBuilderService.find(requestParams, createPageable(requestParams, "lastName"));
    }

    //by id
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<StaffDto> find(@PathVariable("id") Integer id, @RequestParam Map<String, String> requestParams) {
        StaffDto staffDto = staffBuilderService.find(id, requestParams);
        return new RestResponse<>(staffDto);
    }

    //by email
    //by username
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<StaffDto> findByProperty(@RequestParam Map<String, String> requestParams) {
        StaffDto staffDto;
        if (requestParams.containsKey("email")) {
            staffDto = staffBuilderService.findByEmail(requestParams.get("email"));
        } else if (requestParams.containsKey("username")) {
            staffDto = staffBuilderService.findByUsername(requestParams.get("username"));
        } else
            throw new NotFoundException("Cannot find the staff member you are looking for, please use either email or username with this endpoint");
        return new RestResponse<>(staffDto);
    }

    //stores
    @RequestMapping(value = "/{id}/stores", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<StoreDto> findStoresForStaff(@PathVariable("id") Integer id) {
        StoreDto storeDto = staffBuilderService.findStoreByStaffId(id);
        return new RestResponse<>(storeDto);
    }

    //payments
    @RequestMapping(value = "/{id}/payments", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<Set<PaymentDto>> findPaymentsForStaff(@PathVariable("id") Integer id) {
        Set<PaymentDto> payments = staffBuilderService.findPaymentsByStaffId(id);
        return new RestResponse<>(payments, payments.size(), 0, 0, null, null);
    }

    //rentals
    @RequestMapping(value = "/{id}/rentals", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<Set<RentalDto>> findRentals(@PathVariable("id") Integer id) {
        Set<RentalDto> rentals = staffBuilderService.findRentalsByStaffId(id);
        return new RestResponse<>(rentals, rentals.size(), 0, 0, null, null);
    }

    //address
    @RequestMapping(value = "/{id}/address", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<AddressDto> findAddress(@PathVariable("id") Integer id) {
        AddressDto address = staffBuilderService.findStaffAddressById(id);
        return new RestResponse<>(address);
    }
}
