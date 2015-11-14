package com.boole.web.controller.rest;

import com.boole.common.domain.Crew;
import com.boole.common.domain.Movie;
import com.boole.common.domain.Role;
import com.boole.common.domain.dto.CrewDTO;
import com.boole.common.domain.dto.MovieDTO;
import com.boole.common.service.MovieService;
import com.boole.common.service.mapper.CrewMapperService;
import com.boole.common.service.mapper.MovieMapperService;
import com.boole.common.util.exceptions.NotFoundException;
import com.boole.web.controller.rest.components.ResponseMetadata;
import com.boole.web.controller.rest.components.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 10/22/15.
 */
@RestController
@RequestMapping("/api/movies")
public class MovieController extends AbstractRestController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieMapperService movieMapperService;

    @Autowired
    private CrewMapperService crewMapperService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<List<MovieDTO>> findMovies(@RequestParam Map<String, String> requestParams) {
        Page<Movie> moviePage = movieService.findAll(0, 20);

        return new RestResponse<>(movieMapperService.mapMovies(moviePage.getContent()),
                new ResponseMetadata<>(moviePage));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RestResponse<MovieDTO> findMovieById(@PathVariable("id") Long id,
                                                @RequestParam Map<String, String> requestParams) {
        RestResponse<MovieDTO> restResponse = new RestResponse<>(null);
        if (requestParams.containsKey("include")) {
            String includes = requestParams.get("include");
            if (includes.equalsIgnoreCase("details")) {
                Optional<Movie> movieOptional = movieService.findWithFullDetails(id);
                Movie movie = movieOptional
                        .orElseThrow(() ->
                                new NotFoundException("Movie with Id: " + id + " Was not found"));

                restResponse = new RestResponse<>(movieMapperService.mapMovie(movie, "details"));
            }
        } else {
            Optional<Movie> movieOptional = movieService.findOne(id);
            Movie movie = movieOptional
                    .orElseThrow(() ->
                            new NotFoundException("Movie with Id: " + id + " Was not found"));
            restResponse = new RestResponse<>(movieMapperService.mapMovie(movie));
        }

        return restResponse;
    }

    @RequestMapping(value = "/{id}/actors", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RestResponse<List<CrewDTO>> findActorsForMovie(@PathVariable("id") Long id) {
        List<Crew> actors = movieService.findActorsForMovie(id);

        if (actors.size() == 0)
            throw new NotFoundException("No actor was found associated with " +
                    "Movie Using Movie Id: " + id);

        return new RestResponse<>(crewMapperService.mapCrew(actors),
                new ResponseMetadata(actors.size(), actors.size(), 0, 0));
    }


    @RequestMapping(value = "/{id}/writers", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RestResponse<List<CrewDTO>> findWritersForMovie(@PathVariable("id") Long id) {
        List<Crew> writers = movieService.findWriters(id);

        if (writers.size() == 0)
            throw new NotFoundException("No writer was found associated with " +
                    "Movie Using Movie Id: " + id);

        return new RestResponse<>(crewMapperService.mapCrew(writers),
                new ResponseMetadata(writers.size(), writers.size(), 0, 0));
    }

    @RequestMapping(value = "/{id}/producers", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RestResponse<List<CrewDTO>> findProducersForMovie(@PathVariable("id") Long id) {
        List<Crew> producers = movieService.findProducers(id);

        if (producers.size() == 0)
            throw new NotFoundException("No producer was found associated with " +
                    "Movie Using Movie Id: " + id);

        return new RestResponse<>(crewMapperService.mapCrew(producers),
                new ResponseMetadata(producers.size(), producers.size(), 0, 0));
    }


    @RequestMapping(value = "/{id}/directors", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RestResponse<List<CrewDTO>> findDirectorsForMovie(@PathVariable("id") Long id) {
        List<Crew> directors = movieService.findDirectors(id);

        if (directors.size() == 0)
            throw new NotFoundException("No director was found associated with " +
                    "Movie Using Movie Id: " + id);

        return new RestResponse<>(crewMapperService.mapCrew(directors),
                new ResponseMetadata(directors.size(), directors.size(), 0, 0));
    }


    @RequestMapping(value = "/{id}/crews", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RestResponse<List<CrewDTO>> findCrewForMovie(@PathVariable("id") Long id) {
        List<Role> roles = movieService.findRoles(id);

        if (roles.size() == 0)
            throw new NotFoundException("No crew was found associated with " +
                    "Movie Using Movie Id: " + id);

        return new RestResponse<>(crewMapperService.mapRoleToCrew(roles),
                new ResponseMetadata(roles.size(), roles.size(), 0, 0));
    }
}
