package com.boole.controller.components;

import com.boole.controller.rest.dto.CategoryDto;
import com.boole.controller.rest.dto.FilmDto;
import com.boole.controller.rest.dto.ResponseMetadata;
import com.boole.controller.rest.dto.RestResponse;
import com.boole.common.util.exceptions.NotFoundException;
import com.boole.domain.Category;
import com.boole.domain.Film;
import com.boole.domain.FilmCategory;
import com.boole.common.service.CategoryService;
import com.boole.common.util.StringUtil;
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
public class CategoryBuilderService extends BaseBuilderService<CategoryDto> {
    @Autowired
    CategoryService categoryService;

    public List<CategoryDto> findAll(Map<String, String> requestParams) {
        List<CategoryDto> results = new ArrayList<>();
        List<Category> categories = categoryService.findAll();
        if (requestParams.size() <= 0) {
            results = categories
                    .stream()
                    .map(this::categoryToDto)
                    .collect(Collectors.toList());
        } else {
            String includes = requestParams.get(INCLUDE_KEY);

            String[] params = new String[]{};
            if (!StringUtil.isNullOrEmpty(includes)) {
                params = includes.split(",");
            }
            if (params.length > 0) {
                List<String> paramValues = Collections.unmodifiableList(Arrays.asList(params));
                if (paramValues.contains("films") || paramValues.contains("film")) {
                    results = categories.stream()
                            .map(this::getCategoryWithFilms)
                            .collect(Collectors.toList());
                }
            }
        }

        return results;
    }


    public RestResponse<List<CategoryDto>> find(Map<String, String> requestParams, Pageable pageable) {
        List<CategoryDto> results = new ArrayList<>();
        Page<Category> categoryPage = categoryService.findAll(pageable);
        List<Category> categories = categoryPage.getContent();
        if (requestParams.containsKey(INCLUDE_KEY)) {
            String includes = requestParams.get(INCLUDE_KEY);

            String[] params = new String[]{};
            if (!StringUtil.isNullOrEmpty(includes)) {
                params = includes.split(",");
            }
            if (params.length > 0) {
                List<String> paramValues = Collections.unmodifiableList(Arrays.asList(params));
                if (paramValues.contains("films") || paramValues.contains("film")) {
                    results = categories.stream()
                            .map(this::getCategoryWithFilms)
                            .collect(Collectors.toList());
                }
            }
        } else {
            results = categories
                    .stream()
                    .map(this::categoryToDto)
                    .collect(Collectors.toList());
        }

        ResponseMetadata responseMetadata = new ResponseMetadata(categoryPage.getNumberOfElements(),
                categoryPage.getTotalElements(),
                categoryPage.getTotalPages(), categoryPage.getNumber());

        return new RestResponse<>(results, responseMetadata);

    }

    private CategoryDto getCategoryWithFilms(Category category) {
        Set<FilmDto> films = new HashSet<>(0);
        Set<FilmCategory> filmActors = category.getFilmCategories();
        if (filmActors.size() > 0) {
            films = filmActors.stream()
                    .map(FilmCategory::getFilm)
                    .map(this::filmToDto)
                    .collect(Collectors.toSet());
        }

        CategoryDto categoryDto = categoryToDto(category);
        categoryDto.setFilms(films);
        return categoryDto;
    }

    public CategoryDto find(Integer id, Map<String, String> requestParams) {
        Optional<Category> optionalCategory = categoryService.find(id);
        Category category = optionalCategory
                .orElseThrow(() ->
                        new NotFoundException("Category with Id: " + id + " Was not found"));

        CategoryDto categoryDto = categoryToDto(category);
        if (requestParams.size() > 0) {
            String includes = requestParams.get(INCLUDE_KEY);

            String[] params = new String[]{};
            if (!StringUtil.isNullOrEmpty(includes)) {
                params = includes.split(",");
            }
            if (params.length > 0) {
                List<String> paramValues = Collections.unmodifiableList(Arrays.asList(params));
                if (paramValues.contains("films") || paramValues.contains("film")) {
                    categoryDto = categoryToDto(category);
                    Set<Film> films = categoryService.findFilms((int) category.getCategoryId());
                    categoryDto.setFilms(filmsToDto(films));
                }
            }
        }

        return categoryDto;
    }

    private Set<FilmDto> filmsToDto(Set<Film> films) {
        return films
                .stream()
                .map(this::filmToDto)
                .collect(Collectors.toSet());
    }

    private CategoryDto categoryToDto(Category category) {
        return new CategoryDto.Builder()
                .id((int) category.getCategoryId())
                .name(category.getName())
                .lastUpdated(category.getLastUpdated())
                .build();
    }

    public Set<FilmDto> findFilms(Integer id) {
        Set<Film> films = categoryService.findFilms(id);
        return films.stream()
                .map(this::filmToDto)
                .collect(Collectors.toSet());
    }
}
