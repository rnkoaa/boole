package com.boole.common.service.mapper;

import com.boole.common.domain.Movie;
import com.boole.common.domain.Role;
import com.boole.common.domain.dto.CrewDTO;
import com.boole.common.domain.dto.GenreDTO;
import com.boole.common.domain.dto.MovieDTO;
import com.boole.common.util.exceptions.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/13/15.
 */
@Component
public class MovieMapperService {

    @Autowired
    GenreMapperService genreMapperService;

    @Autowired
    CrewMapperService crewMapperService;

    public MovieDTO mapMovie(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setName(movie.getName());
        movieDTO.setCountry(movie.getCountry());
        movieDTO.setLanguage(movie.getLanguage());
        movieDTO.setRating(movie.getRating());
        movieDTO.setReleaseDate(movie.getReleaseDate());
        movieDTO.setReview(movie.getReview());
        movieDTO.setRuntime(movie.getRuntime());
        movieDTO.setSynopsis(movie.getSynopsis());
        movieDTO.setYear(movie.getYear());

        return movieDTO;
    }

    public List<MovieDTO> mapMovies(List<Movie> movies) {
        return movies.stream()
                .map(this::mapMovie)
                .collect(Collectors.toList());
    }

    public List<MovieDTO> mapMovies(List<Movie> movies, Map<String, String> requestParams) {
        List<MovieDTO> movieDTOs = new ArrayList<>(0);
        if (requestParams.containsKey("include")) {
            String includes = requestParams.get("include");
            includes = includes.toLowerCase();
            if (includes.contains("genres") && includes.contains("cast")) {
                //skip this for now.
                throw new NotYetImplementedException("This block of code is not yet Implemented");
            } else if (includes.contains("genres")) {
                movieDTOs = movies.stream()
                        .map(movie -> mapMovie(movie, "genres"))
                        .collect(Collectors.toList());
            } else if (includes.contains("cast")) {
                throw new NotYetImplementedException("This block of code is not yet Implemented");
            }
        } else
            movieDTOs = mapMovies(movies);
        return movieDTOs;
    }

    public List<MovieDTO> mapMovies(List<Movie> movies, String includes) {
        if (includes.equalsIgnoreCase("details")) {
            return movies.stream()
                    .map(movie -> mapMovie(movie, includes))
                    .collect(Collectors.toList());
        }
        return mapMovies(movies);
    }

    public MovieDTO mapMovie(Movie movie, String includes) {
        MovieDTO movieDTO = mapMovie(movie);
        if (includes.equalsIgnoreCase("details")) {
            Set<GenreDTO> genreDTOs = movie.getGenres()
                    .stream()
                    .map(genreMapperService::mapGenre)
                    .collect(Collectors.toSet());

            movieDTO.setGenres(genreDTOs);

            Set<Role> roles = movie.getRoles();
            Set<Role> actorRoles = filter("Cast", roles);
            movieDTO.setActors(mapRoleToCrew(actorRoles));

            //filter out director/directors
            Set<Role> directorRoles = filter("Director", roles);
            movieDTO.setDirectors(mapRoleToCrew(directorRoles));

            //filter out producer/producers
            Set<Role> producerRoles = filter("Producer", roles);
            movieDTO.setProducers(mapRoleToCrew(directorRoles));

            //filter out writer/writers
            Set<Role> writerRoles = filter("Writer", roles);
            movieDTO.setWriters(mapRoleToCrew(writerRoles));
        } else if (includes.equalsIgnoreCase("genres")) {
            Set<GenreDTO> genreDTOs = movie.getGenres()
                    .stream()
                    .map(genreMapperService::mapGenre)
                    .collect(Collectors.toSet());

            movieDTO.setGenres(genreDTOs);
        }
        return movieDTO;
    }


    private Set<CrewDTO> mapRoleToCrew(Set<Role> actorRoles) {
        return actorRoles.stream()
                .map(role -> crewMapperService.mapCrew(role.getCrew()))
                .collect(Collectors.toSet());
    }

    private Set<Role> filter(String key, Set<Role> roles) {
        return roles.stream()
                .filter(role -> role.getJob().equalsIgnoreCase(key))
                .collect(Collectors.toSet());
    }
}
