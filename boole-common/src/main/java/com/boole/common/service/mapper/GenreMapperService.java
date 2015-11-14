package com.boole.common.service.mapper;

import com.boole.common.domain.Genre;
import com.boole.common.domain.dto.GenreDTO;
import org.springframework.stereotype.Component;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/13/15.
 */
@Component
public class GenreMapperService {

    public GenreDTO mapGenre(Genre genre) {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(genre.getId());
        genreDTO.setName(genre.getName());
        return genreDTO;
    }
}
