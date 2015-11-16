package com.boole.common.service.impl;

import com.boole.common.domain.Genre;
import com.boole.common.repository.GenreRepository;
import com.boole.common.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/15/15.
 */
@Service
@Transactional(readOnly = true)
public class GenreServiceImpl extends BaseServiceImpl<Genre> implements GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Optional<Genre> findOne(Long id) {
        Genre genre = genreRepository.findOne(id);
        return Optional.ofNullable(genre);
    }

    @Override
    public Page<Genre> findAll(Pageable pageable) {
        return genreRepository.findAll(pageable);
    }

    @Override
    public Page<Genre> findAll(int page, int itemsPerPage) {
        return genreRepository.findAll(createPageable(page, itemsPerPage, null));
    }

    @Override
    public List<Genre> findAll(Specification<Genre> specification) {
        return genreRepository.findAll(specification);
    }

    @Override
    public Genre findOne(Specification<Genre> specification) {
        return genreRepository.findOne(specification);
    }
}
