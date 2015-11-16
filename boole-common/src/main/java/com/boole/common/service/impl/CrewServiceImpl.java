package com.boole.common.service.impl;

import com.boole.common.domain.Crew;
import com.boole.common.domain.Role;
import com.boole.common.repository.CrewRepository;
import com.boole.common.repository.RoleRepository;
import com.boole.common.service.CrewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.boole.common.repository.specifications.RoleSpecifications.crewWithRoles;
import static com.boole.common.repository.specifications.RoleSpecifications.roleWithCrew;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/14/15.
 */
@Service
@Transactional(readOnly = true)
public class CrewServiceImpl extends BaseServiceImpl<Crew> implements CrewService {

    private static final Logger logger = LoggerFactory.getLogger(CrewServiceImpl.class);

    private final CrewRepository crewRepository;

    private final RoleRepository roleRepository;

    @Autowired
    public CrewServiceImpl(CrewRepository crewRepository, RoleRepository roleRepository) {
        this.crewRepository = crewRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Crew> findOne(Long id) {
        logger.debug("Find Crew With Id: {}", id);
        Crew crew = crewRepository.findOne(id);

        return Optional.ofNullable(crew);
    }

    @Override
    public Page<Crew> findAll(Pageable pageable) {
        logger.debug("Finding Crew with paging");
        return crewRepository.findAll(pageable);
    }

    @Override
    public Page<Crew> findAll(int page, int itemsPerPage) {
        logger.debug("Finding Crew with page {} and number of Items: {}", page, itemsPerPage);
        Pageable pageable = createPageable(page, itemsPerPage, null);
        return findAll(pageable);
    }

    @Override
    public List<Crew> findAll(Specification<Crew> specification) {
        logger.debug("Finding Crews with Using Specification");
        return crewRepository.findAll(specification);
    }

    @Override
    public Crew findOne(Specification<Crew> specification) {
        logger.debug("Finding Crews with Using Specification");
        return crewRepository.findOne(specification);
    }

    @Override
    public List<Role> findWithFullDetails(Long id) {
        logger.debug("Finding Crews with Using Specification");
        return roleRepository.findAll(roleWithCrew(id));

    }

    @Override
    public List<Role> findCrewRoles(String roleType) {
        logger.debug("Finding Crews who are {} Using Specification");
        return roleRepository.findAll(crewWithRoles(roleType));
    }

    @Override
    public Page<Role> findCrewRoles(String roleType, Pageable pageable) {
        logger.debug("Finding Crews who are {} Using Specification");
        return roleRepository.findAll(crewWithRoles(roleType), pageable);
    }
}
