package com.boole.common.repository.specifications;

import com.boole.common.domain.Movie;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;

/**
 * Created on 11/12/2015.
 */
public class MovieSpecifications {

    public static Specification<Movie> movieWithRoles(Long movieId) {
        return (root, query, cb) -> {
            query.distinct(true);
            root.fetch("roles", JoinType.LEFT);
            root.fetch("genres", JoinType.LEFT);
            return cb.equal(root.get("id"), movieId);
        };
    }


}
