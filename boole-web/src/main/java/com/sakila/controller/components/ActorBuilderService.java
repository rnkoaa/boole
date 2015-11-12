package com.sakila.controller.components;

import com.sakila.controller.rest.dto.ActorDto;
import com.sakila.controller.rest.dto.FilmDto;
import com.sakila.controller.rest.dto.ResponseMetadata;
import com.sakila.controller.rest.dto.RestResponse;
import com.sakila.util.exceptions.NotFoundException;
import com.sakila.domain.Actor;
import com.sakila.domain.Film;
import com.sakila.domain.FilmActor;
import com.sakila.service.ActorService;
import com.sakila.util.StringUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created on 10/22/2015.
 */
@Component
@Transactional(readOnly = true)
public class ActorBuilderService extends BaseBuilderService<ActorDto> {

    private static final String INCLUDE_KEY = "include";
    @Autowired
    ActorService actorService;


    @Transactional
    public ActorDto findActor(int id, Map<String, String> requestParams) {
        Optional<Actor> optionalActor = actorService.find(id);
        Actor actor = optionalActor.orElseThrow(() -> new NotFoundException("Actor With Id: " + id + " cannot be found"));

        String includes = null;
        if (requestParams.containsKey(INCLUDE_KEY)) {
            includes = requestParams.get(INCLUDE_KEY);
        }

        String[] params = new String[]{};
        if (!StringUtil.isNullOrEmpty(includes)) {
            params = includes.split(",");
        }

        Set<FilmDto> films = new HashSet<>(0);
        if (params.length > 0) {
            List<String> paramValues = Collections.unmodifiableList(Arrays.asList(params));
            if (paramValues.contains("films") || paramValues.contains("film")) {
                Set<FilmActor> filmActors = actor.getFilmActors();
                if (filmActors.size() > 0) {
                    films = filmActors.stream()
                            .map(FilmActor::getFilm)
                            .map(this::filmToDto)
                            .collect(Collectors.toSet());
                }
            }
        }

        return buildActorDto(actor, films);
    }

    @Transactional
    public RestResponse<List<ActorDto>> find(@RequestParam Map<String, String> requestParams, Pageable pageable) {
        List<ActorDto> results = new ArrayList<>();
        Page<Actor> actorPage = actorService.findAll(pageable);
        List<Actor> actors = actorPage.getContent();

        if (requestParams.containsKey(INCLUDE_KEY)) {
            String includes = requestParams.get(INCLUDE_KEY);

            String[] params = new String[]{};
            if (!StringUtil.isNullOrEmpty(includes)) {
                params = includes.split(",");
            }
            List<String> paramValues = Collections.unmodifiableList(Arrays.asList(params));
            if (paramValues.contains("films") || paramValues.contains("film")) {
                actors.forEach(Actor::getFilmActors);
                results = actors.stream()
                        .map(this::getActorDtoWithFilm)
                        .collect(Collectors.toList());
            }
        } else {
            results = actors
                    .stream()
                    .map(actor -> buildActorDto(actor, new HashSet<>()))
                    .collect(Collectors.toList());
        }


        ResponseMetadata responseMetadata = new ResponseMetadata(actorPage.getNumberOfElements(), actorPage.getTotalElements(),
                actorPage.getTotalPages(), actorPage.getNumber());

        return new RestResponse<>(results, responseMetadata);
    }


    private ActorDto getActorDtoWithFilm(Actor actor) {
        Set<FilmDto> films = getFilmsForActor(actor);
        return buildActorDto(actor, films);
    }

    private Set<FilmDto> getFilmsForActor(Actor actor) {
        Set<FilmDto> films = new HashSet<>(0);
        Set<FilmActor> filmActors = actor.getFilmActors();
        if (filmActors.size() > 0) {
            films = filmActors.stream()
                    .map(FilmActor::getFilm)
                    .map(this::filmToDto)
                    .collect(Collectors.toSet());
        }
        return films;
    }

    private ActorDto buildActorDto(Actor actor, Set<FilmDto> films) {
        return new ActorDto.Builder().id(actor.getActorId())
                .firstName(actor.getFirstName())
                .lastName(actor.getLastName())
                .films(films)
                .lastUpdated(new DateTime(actor.getLastUpdated().getTime()))
                .build();
    }

    public Set<FilmDto> findFilms(int id) {
        Set<Film> films = actorService.findFilms(id);
        return films.stream()
                .map(this::filmToDto)
                .collect(Collectors.toSet());
    }
}
