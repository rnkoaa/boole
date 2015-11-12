package com.boole.common.repository.specifications;

import com.boole.common.domain.Movie;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;

/**
 * Created on 11/12/2015.
 */
public class MovieSpecifications {

    public static Specification<Movie> movieWithRolesById(Long movieId) {
        return (root, query, cb) -> {
            query.distinct(true);
            root.fetch("roles", JoinType.INNER);
            return cb.equal(root.get("id"), movieId);
        };
    }

    public static Specification<Movie> movieWithGenresById(Long movieId) {
        return (root, query, cb) -> {
            query.distinct(true);
            root.fetch("genres", JoinType.INNER);
            return cb.equal(root.get("id"), movieId);
        };
    }
    public static Specification<Movie> movieWithGenresAndRolesById(Long movieId) {
        return (root, query, cb) -> {
            query.distinct(true);
            root.fetch("genres", JoinType.INNER);
            root.fetch("roles", JoinType.INNER);
            return cb.equal(root.get("id"), movieId);
        };
    }


}
