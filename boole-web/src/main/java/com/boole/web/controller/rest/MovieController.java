package com.boole.web.controller.rest;

import com.boole.common.domain.Movie;
import com.boole.common.service.MovieService;
import com.boole.web.controller.rest.components.ResponseMetadata;
import com.boole.web.controller.rest.components.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 10/22/15.
 */
@RestController
@RequestMapping("/api/movies")
public class MovieController extends AbstractRestController {

    @Autowired
    private MovieService movieService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<List<Movie>> findMovies(@RequestParam Map<String, String> requestParams) {
        Page<Movie> moviePage = movieService.findAll(0, 20);
        return new RestResponse<>(moviePage.getContent(), new ResponseMetadata<>(moviePage));
    }
/*
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
    }*/
}
