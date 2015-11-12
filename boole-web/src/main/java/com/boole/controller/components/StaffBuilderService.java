package com.boole.controller.components;

import com.boole.controller.rest.dto.*;
import com.boole.common.util.exceptions.NotFoundException;
import com.boole.domain.*;
import com.boole.common.service.StaffService;
import com.boole.common.util.StringUtil;
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
public class StaffBuilderService extends BaseBuilderService<StaffDto> {

    private final StaffService staffService;

    @Autowired
    public StaffBuilderService(StaffService staffService) {
        this.staffService = staffService;
    }

    public List<StaffDto> find(Map<String, String> requestParams) {
        List<Staff> staffList = staffService.findAll();
        return findStaff(requestParams, staffList);
    }

    @Override
    public RestResponse<List<StaffDto>> find(Map<String, String> requestParams, Pageable pageable) {
        Page<Staff> staffPage = staffService.findAll(pageable);
        List<StaffDto> results = findStaff(requestParams, staffPage.getContent());

        ResponseMetadata responseMetadata = new ResponseMetadata(staffPage.getNumberOfElements(),
                staffPage.getTotalElements(),
                staffPage.getTotalPages(), staffPage.getNumber());

        return new RestResponse<>(results, responseMetadata);
    }

    protected List<StaffDto> findStaff(Map<String, String> requestParams, List<Staff> staffList) {
        List<StaffDto> results;
        if (!requestParams.containsKey(INCLUDE_KEY)) {
            results = staffList
                    .stream()
                    .map(this::staffToDto)
                    .collect(Collectors.toList());
        } else {
            results = findStaffWithParams(requestParams, staffList);
        }
        return results;
    }

    protected List<StaffDto> findStaffWithParams(Map<String, String> requestParams, List<Staff> staffList) {
        List<StaffDto> results = new ArrayList<>(0);
        String includes = requestParams.get(INCLUDE_KEY);

        String[] params = new String[]{};
        if (!StringUtil.isNullOrEmpty(includes)) {
            params = includes.split(",");
        }
        if (params.length > 0) {
            List<String> paramValues = Collections.unmodifiableList(Arrays.asList(params));
            if (paramValues.contains("all") || paramValues.contains("details")) {
                results =
                        staffList
                                .stream()
                                .map(this::staffToDtoWithDetails)
                                .collect(Collectors.toList());
            }
            if (paramValues.contains("store")) {
                results =
                        staffList
                                .stream()
                                .map(this::staffToDtoWithStore)
                                .collect(Collectors.toList());
            }

            if (paramValues.contains("address")) {
                results =
                        staffList
                                .stream()
                                .map(this::staffToDtoWithAddress)
                                .collect(Collectors.toList());
            }

            if (paramValues.contains("payments") || paramValues.contains("payment")) {
                results =
                        staffList
                                .stream()
                                .map(this::staffDtoWithPayments)
                                .collect(Collectors.toList());
            }

            if (paramValues.contains("rentals") || paramValues.contains("rental")) {
                results =
                        staffList
                                .stream()
                                .map(this::staffToDtoWithRentals)
                                .collect(Collectors.toList());
            }

        }
        return results;
    }

    public StaffDto find(Integer id, Map<String, String> requestParams) {
        Optional<Staff> optionalStaff = staffService.find(id);
        Staff staff = optionalStaff
                .orElseThrow(() -> new NotFoundException("Staff with Id: " + id + " was not found"));
        StaffDto staffDto = null;
        if (requestParams.size() <= 0) {
            staffDto = staffToDto(staff);
        } else {
            String includes = requestParams.get(INCLUDE_KEY);

            String[] params = new String[]{};
            if (!StringUtil.isNullOrEmpty(includes)) {
                params = includes.split(",");
            }
            if (params.length > 0) {
                List<String> paramValues = Collections.unmodifiableList(Arrays.asList(params));
                if (paramValues.contains("all") || paramValues.contains("details")) {
                    staffDto = staffToDtoWithDetails(staff);
                } else {
                    if (paramValues.contains("payments")) {
                        staffDto = staffDtoWithPayments(staff);
                    }
                    if (paramValues.contains("rentals")) {
                        staffDto = staffToDtoWithRentals(staff);
                    }
                    if (paramValues.contains("store")) {
                        staffDto = staffToDtoWithStore(staff);
                    }
                    if (paramValues.contains("address")) {
                        staffDto = staffToDtoWithAddress(staff);
                    }
                }
            }
        }
        return staffDto;
    }


    public StaffDto findByEmail(String email) {
        return staffToDto(staffService.findByEmail(email)
                .orElseThrow(() ->
                        new NotFoundException("We could not find a Staff associated with " +
                                "email: " + email)));
    }

    public StaffDto findByUsername(String username) {
        return staffToDto(staffService.findByUsername(username)
                .orElseThrow(() ->
                        new NotFoundException("We could not find a Staff associated with " +
                                "username: " + username)));
    }

    public StoreDto findStoreByStaffId(Integer id) {
        return storeToDto(staffService.findStoreForStaff(id)
                .orElseThrow(() ->
                        new NotFoundException("We could not find a Staff associated with " +
                                "Address with Staff id: " + id)));
    }

    public Set<PaymentDto> findPaymentsByStaffId(Integer id) {
        Set<Payment> payments = staffService.findPaymentsByStaffId(id);
        return paymentsToDto(payments);
    }

    public Set<RentalDto> findRentalsByStaffId(Integer id) {
        Set<Rental> rentals = staffService.findRentalsByStaffId(id);
        return rentals.stream()
                .map(this::rentalToDto)
                .collect(Collectors.toSet());
    }

    public AddressDto findStaffAddressById(Integer id) {
        return addressToDto(staffService.findAddressForStaff(id)
                .orElseThrow(() ->
                        new NotFoundException("We could not find a Staff associated with " +
                                "Address with Staff id: " + id)));
    }
}
