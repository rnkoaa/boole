package com.boole.controller.rest;

import com.boole.controller.components.FilmBuilderService;
import com.boole.controller.rest.dto.ActorDto;
import com.boole.controller.rest.dto.FilmDto;
import com.boole.controller.rest.dto.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 10/22/15.
 */
@RestController
@RequestMapping("/api/films")
public class FilmController extends AbstractRestController {

    @Autowired
    private FilmBuilderService filmBuilderService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<List<FilmDto>> findFilms(@RequestParam Map<String, String> requestParams) {
        return filmBuilderService.find(requestParams, createPageable(requestParams, "title"));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RestResponse<FilmDto> findFilmById(@PathVariable("id") int id,
                                              @RequestParam Map<String, String> requestParams) {
        return new RestResponse<>(filmBuilderService.findFilm(id, requestParams));
    }

    @RequestMapping(value = "/{id}/actors", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RestResponse<Set<ActorDto>> findActorsForFilm(@PathVariable("id") int id) {
        Set<ActorDto> response = filmBuilderService.findActors(id);
        return new RestResponse<>(response, response.size(), 0, 0, null, null);
    }

    @RequestMapping(value = "/{id}/categories", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RestResponse<Set<String>> findCategoriesForFilm(@PathVariable("id") int id) {
        Set<String> response = filmBuilderService.findCategories(id);
        return new RestResponse<>(response, response.size(), 0, 0, null, null);
    }
}
