package com.boole.common.service;

import com.boole.common.domain.Crew;
import com.boole.common.domain.Movie;
import com.boole.common.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Created on 11/12/2015.
 */
public interface CrewService extends BaseService<Crew>{
    List<Role> findWithFullDetails(Long id);

    List<Role> findCrewRoles(String roleType);

    Page<Role> findCrewRoles(String roleType, Pageable pageable);
}
