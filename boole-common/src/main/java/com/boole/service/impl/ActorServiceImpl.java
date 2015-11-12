package com.sakila.service.impl;

import com.sakila.domain.Actor;
import com.sakila.domain.Film;
import com.sakila.domain.FilmActor;
import com.sakila.repository.ActorRepository;
import com.sakila.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created on 10/21/2015.
 */
@Service
@Transactional(readOnly = true)
public class ActorServiceImpl extends BaseServiceImpl<Actor> implements ActorService {

    private final ActorRepository actorRepository;

    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public Optional<Actor> find(Integer id) {
        Actor actor = actorRepository.findOne(id);

        return Optional.ofNullable(actor);
    }

    @Override
    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    @Override
    @Transactional
    public Actor save(Actor actor) {
        Assert.notNull(actor, "Actor object cannot be null while saving.");
        return actorRepository.save(actor);
    }

    @Override
    public Set<Film> findFilms(Integer id) {
        Actor actor = actorRepository.findOne(id);
        Set<Film> films = new HashSet<>(0);
        if (actor != null) {
            films = actor.getFilmActors()
                    .stream()
                    .map(FilmActor::getFilm)
                    .collect(Collectors.toSet());
        }
        return films;
    }

    @Override
    @Transactional
    public Actor update(Actor actor) {
        Assert.notNull(actor, "Actor object cannot be null while updating.");
        return actorRepository.save(actor);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Assert.notNull(id, "id cannot be null while updating.");
        actorRepository.delete(id);
    }

    @Override
    public Page<Actor> findAll(Pageable pageable) {
        return actorRepository.findAll(pageable);
    }
}
