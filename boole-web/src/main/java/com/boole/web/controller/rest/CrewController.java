package com.boole.web.controller.rest;

import com.boole.common.domain.Crew;
import com.boole.common.domain.Role;
import com.boole.common.domain.dto.CrewDTO;
import com.boole.common.service.CrewService;
import com.boole.common.service.mapper.CrewMapperService;
import com.boole.common.util.StringUtil;
import com.boole.common.util.exceptions.NotFoundException;
import com.boole.web.controller.rest.components.ResponseMetadata;
import com.boole.web.controller.rest.components.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/14/15.
 */
@RestController
@RequestMapping("/api/crews")
public class CrewController extends AbstractRestController {

    @Autowired
    private CrewService crewService;

    @Autowired
    private CrewMapperService crewMapperService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<List<CrewDTO>> findCrew(@RequestParam Map<String, String> requestParams) {
        RestResponse<List<CrewDTO>> restResponse = new RestResponse<>(null);
        Pageable pageable = createPageable(requestParams, "");
        if (requestParams.containsKey("role")) {
            String roleType = requestParams.get("role");
            roleType = StringUtil.capitalize(roleType);
            Page<Role> crewPage = crewService.findCrewRoles(roleType, pageable);

            List<Role> crewRoles = crewPage.getContent();
            if (crewRoles.size() > 0) {
                List<CrewDTO> crewDTOs = crewMapperService.mapRolesToCrews(crewRoles);
                restResponse = new RestResponse<>(crewDTOs, new ResponseMetadata<>(crewPage));
            } else {
                throw new NotFoundException("There are no crews with with the role: " + roleType);
            }
        } else {
            Page<Crew> crewPage = crewService.findAll(pageable);

            return new RestResponse<>(crewMapperService.mapCrew(crewPage.getContent()),
                    new ResponseMetadata<>(crewPage));
        }
        return restResponse;

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RestResponse<CrewDTO> findMovieById(@PathVariable("id") Long id,
                                               @RequestParam Map<String, String> requestParams) {
        RestResponse<CrewDTO> restResponse = new RestResponse<>(null);
        if (requestParams.containsKey("include")) {
            String includes = requestParams.get("include");
            if (includes.equalsIgnoreCase("details")) {

                List<Role> crewRoles = crewService.findWithFullDetails(id);
                if (crewRoles.size() > 0) {
                    CrewDTO crewDTO = crewMapperService.mapRoleDetailsToCrew(crewRoles);
                    restResponse = new RestResponse<>(crewDTO);
                } else {
                    throw new NotFoundException("There are no crews with Id: " + id);
                }
            }
        } else {
            /*Optional<Movie> movieOptional = movieService.findOne(id);
            Movie movie = movieOptional
                    .orElseThrow(() ->
                            new NotFoundException("Movie with Id: " + id + " Was not found"));
            restResponse = new RestResponse<>(movieMapperService.mapMovie(movie));*/
        }

        return restResponse;
    }
}
