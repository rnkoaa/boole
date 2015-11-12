package com.sakila.service.impl;

import com.sakila.domain.Category;
import com.sakila.domain.Film;
import com.sakila.domain.FilmCategory;
import com.sakila.repository.CategoryRepository;
import com.sakila.service.CategoryService;
import com.sakila.util.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
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
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<Category> find(Integer id) {
        Category category = categoryRepository.findOne(id.byteValue());

        return Optional.ofNullable(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        Assert.notNull(category, "Category object cannot be null while saving.");
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        Assert.notNull(category, "Category object cannot be null while saving.");
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Integer id) {
        Assert.notNull(id, "Id object cannot be null while saving.");
        categoryRepository.delete(id.byteValue());
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Set<Film> findFilms(Integer categoryId) {
        Optional<Category> categoryOptional = find(categoryId);
        Category category = categoryOptional
                .orElseThrow(() -> new NotFoundException("Category With id: " + categoryId + " was not found"));
        Set<Film> films = new HashSet<>(0);
        if (category != null) {
            films = category.getFilmCategories()
                    .stream()
                    .map(FilmCategory::getFilm)
                    .collect(Collectors.toSet());
        }
        return films;
    }
}
