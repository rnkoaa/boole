package com.sakila.controller.rest;

import com.sakila.controller.components.CategoryBuilderService;
import com.sakila.controller.rest.dto.CategoryDto;
import com.sakila.controller.rest.dto.FilmDto;
import com.sakila.controller.rest.dto.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created on 10/21/2015.
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController extends AbstractRestController {

    @Autowired
    private CategoryBuilderService categoryBuilderService;

    @RequestMapping(method = RequestMethod.GET)
    public RestResponse<List<CategoryDto>> findCategories(@RequestParam Map<String, String> requestParams) {
        Pageable pageable = createPageable(requestParams, "name");
        return categoryBuilderService.find(requestParams, pageable);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<CategoryDto> findCategory(@PathVariable("id") Integer id,
                                                  @RequestParam Map<String, String> requestParams) {
        CategoryDto categoryDto = categoryBuilderService.find(id, requestParams);
        return new RestResponse<>(categoryDto);
    }

    @RequestMapping(value = "/{id}/films", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<Set<FilmDto>> findFilmsForCategory(@PathVariable("id") Integer id) {
        Set<FilmDto> filmDtos = categoryBuilderService.findFilms(id);
        return new RestResponse<>(filmDtos, filmDtos.size(), 0, 0, null, null);
    }
}
