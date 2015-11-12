package com.sakila.controller.components;

import com.sakila.controller.rest.dto.ActorDto;
import com.sakila.controller.rest.dto.FilmDto;
import com.sakila.controller.rest.dto.ResponseMetadata;
import com.sakila.controller.rest.dto.RestResponse;
import com.sakila.util.exceptions.NotFoundException;
import com.sakila.domain.Actor;
import com.sakila.domain.Category;
import com.sakila.domain.Film;
import com.sakila.service.FilmService;
import com.sakila.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 10/22/15.
 */
@Component
@Transactional(readOnly = true)
public class FilmBuilderService extends BaseBuilderService<FilmDto> {

    @Autowired
    FilmService filmService;

    public List<FilmDto> findAll(Map<String, String> requestParams) {
        List<FilmDto> results = new ArrayList<>();
        List<Film> films = filmService.findAll();

        if (!requestParams.containsKey(INCLUDE_KEY)) {
            results = films.stream()
                    .map(this::filmToDto)
                    .collect(Collectors.toList());
        } else {
            String includes = requestParams.get(INCLUDE_KEY);

            String[] params = new String[]{};
            if (!StringUtil.isNullOrEmpty(includes)) {
                params = includes.split(",");
            }
            if (params.length > 0) {
                List<String> paramValues = Collections.unmodifiableList(Arrays.asList(params));
                List<FilmDto> filmDtos = films.stream()
                        .map(this::filmToDto)
                        .collect(Collectors.toList());
                if (paramValues.contains("actors") || paramValues.contains("actor")) {

                    results = filmDtos.stream()
                            .map(filmDto -> {
                                Set<Actor> actors = filmService.findActors(filmDto.getId());
                                filmDto.setActors(actorToDtos(actors));
                                return filmDto;
                            })
                            .collect(Collectors.toList());
                }

                if (paramValues.contains("category") || paramValues.contains("categories")) {
                    results = filmDtos.stream()
                            .map(filmDto -> {
                                Set<Category> categories = filmService.findCategories(filmDto.getId());
                                filmDto.setCategories(categoriesToDto(categories));
                                return filmDto;
                            })
                            .collect(Collectors.toList());
                }

            }
        }

        return results;
    }


    @Override
    public RestResponse<List<FilmDto>> find(Map<String, String> requestParams, Pageable pageable) {
        List<FilmDto> results = new ArrayList<>();
        Page<Film> filmPage = filmService.findAll(pageable);
        List<Film> films = filmPage.getContent();

        if (!requestParams.containsKey(INCLUDE_KEY)) {
            results = films.stream()
                    .map(this::filmToDto)
                    .collect(Collectors.toList());
        } else {
            String includes = requestParams.get(INCLUDE_KEY);

            String[] params = new String[]{};
            if (!StringUtil.isNullOrEmpty(includes)) {
                params = includes.split(",");
            }
            if (params.length > 0) {
                List<String> paramValues = Collections.unmodifiableList(Arrays.asList(params));
                List<FilmDto> filmDtos = films.stream()
                        .map(this::filmToDto)
                        .collect(Collectors.toList());
                if (paramValues.contains("actors") || paramValues.contains("actor")) {

                    results = filmDtos.stream()
                            .map(filmDto -> {
                                Set<Actor> actors = filmService.findActors(filmDto.getId());
                                filmDto.setActors(actorToDtos(actors));
                                return filmDto;
                            })
                            .collect(Collectors.toList());
                }

                if (paramValues.contains("category") || paramValues.contains("categories")) {
                    results = filmDtos.stream()
                            .map(filmDto -> {
                                Set<Category> categories = filmService.findCategories(filmDto.getId());
                                filmDto.setCategories(categoriesToDto(categories));
                                return filmDto;
                            })
                            .collect(Collectors.toList());
                }

            }
        }

        ResponseMetadata responseMetadata = new ResponseMetadata(filmPage.getNumberOfElements(),
                filmPage.getTotalElements(),
                filmPage.getTotalPages(), filmPage.getNumber());

        return new RestResponse<>(results, responseMetadata);
    }


    public FilmDto findFilm(int id, Map<String, String> requestParams) {
        Optional<Film> optionalFilm = filmService.find(id);
        Film film = optionalFilm
                .orElseThrow(() ->
                        new NotFoundException("Film with Id: " + id + " Was not found"));

        FilmDto filmDto = filmToDto(film);
        if (requestParams.size() > 0) {
            String includes = requestParams.get(INCLUDE_KEY);

            String[] params = new String[]{};
            if (!StringUtil.isNullOrEmpty(includes)) {
                params = includes.split(",");
            }
            if (params.length > 0) {
                List<String> paramValues = Collections.unmodifiableList(Arrays.asList(params));
                if (paramValues.contains("actors") || paramValues.contains("actor")) {
                    filmDto = filmToDto(film);
                    Set<Actor> actors = filmService.findActors(film.getFilmId());
                    filmDto.setActors(actorToDtos(actors));
                }
            }
        }

        return filmDto;
    }

    public Set<ActorDto> findActors(int id) {
        Set<Actor> actors = filmService.findActors(id);
        return actorToDtos(actors);
    }

    public Set<String> findCategories(int id) {
        Set<Category> filmCategories = filmService.findCategories(id);
        return filmCategories.stream()
                .map(Category::getName)
                .collect(Collectors.toSet());
    }
}
