package com.boole.common.service.mapper;

import com.boole.common.domain.Crew;
import com.boole.common.domain.Role;
import com.boole.common.domain.dto.CrewDTO;
import com.boole.common.domain.dto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/13/15.
 */
@Component
public class CrewMapperService {

    @Autowired
    private RoleMapperService roleMapperService;

    public CrewDTO mapCrew(Crew crew) {
        CrewDTO crewDTO = new CrewDTO();
        crewDTO.setName(crew.getName());
        crewDTO.setId(crew.getId());
        return crewDTO;
    }

    public CrewDTO mapRoleToCrew(Role role) {
        CrewDTO crewDTO = new CrewDTO();
        crewDTO.setName(role.getCrew().getName());
        crewDTO.setId(role.getCrew().getId());
        crewDTO.setRole(role.getJob());
        return crewDTO;
    }
/*
    public CrewDTO mapRoleToCrewWithMovie(Role role) {
        CrewDTO crewDTO = new CrewDTO();
        crewDTO.setName(role.getCrew().getName());
        crewDTO.setId(role.getCrew().getId());
        crewDTO.setMovie(role.getMovie());
        return crewDTO;
    }*/

    public List<CrewDTO> mapCrew(List<Crew> crews) {
        return crews.stream()
                .map(this::mapCrew)
                .collect(Collectors.toList());

    }

    public List<CrewDTO> mapRoleToCrew(List<Role> roles) {
        return roles.stream()
                .map(this::mapRoleToCrew)
                .collect(Collectors.toList());
    }

    public CrewDTO mapRoleDetailsToCrew(List<Role> crewRoles) {
        Role role = crewRoles.get(0);
        CrewDTO crewDTO = new CrewDTO();
        crewDTO.setName(role.getCrew().getName());
        crewDTO.setId(role.getCrew().getId());
        List<RoleDto> roleDtos = roleMapperService.mapRoles(crewRoles);
        crewDTO.setMovieRoles(roleDtos);
        return crewDTO;
    }

    public List<CrewDTO> mapRolesToCrews(List<Role> crewRoles) {
       return crewRoles.stream()
                .map(this::mapRoleToCrew)
                .collect(Collectors.toList());
    }
}
