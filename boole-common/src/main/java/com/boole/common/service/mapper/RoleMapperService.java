package com.boole.common.service.mapper;

import com.boole.common.domain.Movie;
import com.boole.common.domain.Role;
import com.boole.common.domain.dto.MovieDTO;
import com.boole.common.domain.dto.RoleDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/14/15.
 */
@Component
public class RoleMapperService {
    public List<RoleDto> mapRoles(List<Role> crewRoles) {
        return crewRoles.stream()
                .map(this::mapToRoleDto)
                .collect(Collectors.toList());
    }

    private RoleDto mapToRoleDto(Role role) {

        MovieDTO movieDTO = new MovieDTO();
        Movie movie = role.getMovie();
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
        return new RoleDto(movieDTO, role.getJob());
    }
}
