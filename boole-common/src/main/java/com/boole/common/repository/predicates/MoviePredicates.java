package com.boole.common.repository.predicates;

import com.boole.common.domain.Genre;
import com.boole.common.domain.Movie;
import com.boole.common.domain.Role;

import javax.persistence.criteria.*;

/**
 * Created on 11/12/2015.
 */
public class MoviePredicates {

   /* public static Predicate findRolesPredicate(Long movieId, Root<Process> processRoot, CriteriaBuilder cb) {
        Join<Movie, Role> movieRoleJoin = processRoot.join("roles", JoinType.LEFT);
       *//* Join<Movie, Genre> movieGenreJoin = movieRoleJoin.join("genres", JoinType.LEFT);
        return cb.selectCase(movieGenreJoin)*//*
    }*/
}
