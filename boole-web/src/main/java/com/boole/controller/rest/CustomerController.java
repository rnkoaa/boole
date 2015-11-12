package com.boole.controller.rest;

import com.boole.controller.components.CustomerBuilderService;
import com.boole.controller.rest.dto.CustomerDto;
import com.boole.controller.rest.dto.PaymentDto;
import com.boole.controller.rest.dto.RentalDto;
import com.boole.controller.rest.dto.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created on 10/21/2015.
 */
@RestController
@RequestMapping("/api/customers")
public class CustomerController extends AbstractRestController {

    private static final String DEFAULT_SORT_FIELD = "lastName";
    @Autowired
    public CustomerBuilderService customerBuilderService;

    //customers
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<List<CustomerDto>> findAllCustomers(
            @RequestParam Map<String, String> requestParams) {
        Pageable pageable = createPageable(requestParams, DEFAULT_SORT_FIELD);

        return customerBuilderService.find(requestParams, pageable);
    }
    //id

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<CustomerDto> findCustomerById(@PathVariable("id") Integer id,
                                                      @RequestParam Map<String, String> requestParams) {
        CustomerDto customerDto = customerBuilderService.find(id, requestParams);
        return new RestResponse<>(customerDto);
    }

    //id/rentals
    @RequestMapping(value = "/{id}/rentals", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<Set<RentalDto>> findCustomerRentalsById(@PathVariable("id") Integer id) {
        Set<RentalDto> rentalDtos = customerBuilderService.findRentals(id);
        return new RestResponse<>(rentalDtos, rentalDtos.size(), 0, 0, null, null);
    }

    //id/payments
    @RequestMapping(value = "/{id}/payments", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<Set<PaymentDto>> findCustomerPaymentsById(@PathVariable("id") Integer id) {
        Set<PaymentDto> paymentDtos = customerBuilderService.findPayments(id);
        return new RestResponse<>(paymentDtos, paymentDtos.size(), 0, 0, null, null);
    }
    //id/store
}
