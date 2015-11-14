package com.boole.common.service.mapper;

import com.boole.common.domain.Crew;
import com.boole.common.domain.dto.CrewDTO;
import org.springframework.stereotype.Component;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/13/15.
 */
@Component
public class CrewMapperService {

    public CrewDTO mapCrew(Crew crew) {
        CrewDTO crewDTO = new CrewDTO();
        crewDTO.setName(crew.getName());
        crewDTO.setId(crew.getId());
        return crewDTO;
    }
}
