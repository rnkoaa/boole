package com.sakila.service.impl;

import com.sakila.domain.*;
import com.sakila.repository.FilmRepository;
import com.sakila.service.FilmService;
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
 * Created using Intellij IDE
 * Created by rnkoaa on 10/22/15.
 */
@Service
public class FilmServiceImpl extends BaseServiceImpl<Film> implements FilmService {
    private final FilmRepository filmRepository;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public Optional<Film> find(Integer id) {
        Film film = filmRepository.findOne(id);

        return Optional.ofNullable(film);
    }

    @Override
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    @Override
    @Transactional
    public Film save(Film film) {
        Assert.notNull(film, "Film object cannot be null while saving.");
        return filmRepository.save(film);
    }

    @Override
    public Set<Actor> findActors(Integer id) {
        Set<Actor> actors = new HashSet<>(0);
        Optional<Film> optionalFilm = find(id);
        if (optionalFilm.isPresent()) {
            Film film = optionalFilm.get();
            actors = film.getFilmActors()
                    .stream()
                    .map(FilmActor::getActor)
                    .collect(Collectors.toSet());
        }
        return actors;
    }

    @Override
    public Set<Category> findCategories(Integer id) {
        Set<Category> categories = new HashSet<>(0);
        Optional<Film> optionalFilm = find(id);
        if (optionalFilm.isPresent()) {
            Film film = optionalFilm.get();
            categories = film.getFilmCategories()
                    .stream()
                    .map(FilmCategory::getCategory)
                    .collect(Collectors.toSet());
        }
        return categories;
    }

    @Override
    @Transactional
    public Film update(Film film) {
        Assert.notNull(film, "Film object cannot be null while updating.");
        return filmRepository.save(film);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Assert.notNull(id, "id cannot be null while updating.");
        filmRepository.delete(id);
    }

    @Override
    public Page<Film> findAll(Pageable pageable) {
        return filmRepository.findAll(pageable);
    }


}
