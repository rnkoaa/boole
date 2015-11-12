package com.sakila.controller.rest;

import com.sakila.controller.components.ActorBuilderService;
import com.sakila.controller.rest.dto.ActorDto;
import com.sakila.controller.rest.dto.FilmDto;
import com.sakila.controller.rest.dto.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created on 10/21/2015.
 */
@RestController
@RequestMapping("/api/actors")
public class ActorController extends AbstractRestController {

    private static final Logger logger = LoggerFactory.getLogger(ActorController.class);
    private static final String DEFAULT_SORT_FIELD = "lastName";
    @Autowired
    private ActorBuilderService actorBuilderService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<List<ActorDto>> findActors(@RequestParam Map<String, String> requestParams) {
        Pageable pageable = createPageable(requestParams, DEFAULT_SORT_FIELD);
        return actorBuilderService.find(requestParams, pageable);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RestResponse<ActorDto> findActorById(@PathVariable("id") int id, @RequestParam Map<String, String> requestParams) {
        ActorDto actorDto = actorBuilderService.findActor(id, requestParams);
        return new RestResponse<>(actorDto);
    }

    @RequestMapping(value = "/{id}/films", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RestResponse<Set<FilmDto>> findFilmsForActor(@PathVariable("id") int id) {
        Set<FilmDto> filmDtos = actorBuilderService.findFilms(id);
        return new RestResponse<>(filmDtos);
    }
}
