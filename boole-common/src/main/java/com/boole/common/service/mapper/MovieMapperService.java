package com.boole.common.service.mapper;

import com.boole.common.domain.Movie;
import com.boole.common.domain.dto.MovieDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/13/15.
 */
@Component
public class MovieMapperService {
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
}
