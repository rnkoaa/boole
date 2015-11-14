package com.boole.common.repository.specifications;

import com.boole.common.domain.Movie;
import com.boole.common.domain.Role;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/14/15.
 */
public class RoleSpecifications {

    public static Specification<Role> actorRolesForMovie(Long movieId) {
        return (root, query, cb) -> {
            query.distinct(true);
            root.fetch("crew", JoinType.LEFT);
            Path<Movie> moviePath = root.get("movie");
            Path<Long> idPath = moviePath.get("id");
            return cb.and(cb.equal(idPath, movieId),
                    cb.equal(root.get("job"), "Cast"));
        };
    }

    public static Specification<Role> rolesForMovie(Long movieId, String role) {
        return (root, query, cb) -> {
            query.distinct(true);
            root.fetch("crew", JoinType.LEFT);
            Path<Movie> moviePath = root.get("movie");
            Path<Long> idPath = moviePath.get("id");
            return cb.and(cb.equal(idPath, movieId),
                    cb.equal(root.get("job"), role));
        };
    }

    public static Specification<Role> rolesForMovie(Long movieId) {
        return (root, query, cb) -> {
            query.distinct(true);
            root.fetch("crew", JoinType.LEFT);
            Path<Movie> moviePath = root.get("movie");
            Path<Long> idPath = moviePath.get("id");
            return cb.equal(idPath, movieId);
        };
    }

    /**
     *getPath(Long.class, root, "movie.id");
     *@SuppressWarnings("unchecked")
    private <T, R> Path<R> getPath(Class<R> resultType, Path<T> root, String path) {
    String[] pathElements = path.split("\\.");
    Path<?> retVal = root;
    for (String pathEl : pathElements) {
    retVal = (Path<R>) retVal.get(pathEl);
    }
    return (Path<R>) retVal;
    }*/
}
