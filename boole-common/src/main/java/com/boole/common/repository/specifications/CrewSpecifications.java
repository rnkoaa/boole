package com.boole.common.repository.specifications;

import com.boole.common.domain.Crew;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/14/15.
 */
public class CrewSpecifications {
/*
    public static Specification<Crew> crewWithRoles(Long crewId) {
        return (root, query, cb) -> {
            query.distinct(true);
            root.fetch("roles", JoinType.LEFT);
            return cb.equal(root.get("id"), crewId);
        };
    }
*/

}
