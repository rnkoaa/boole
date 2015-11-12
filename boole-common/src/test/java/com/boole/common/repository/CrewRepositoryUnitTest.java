package com.boole.common.repository;

import com.boole.common.AbstractBooleCommonTest;
import com.boole.common.domain.Crew;
import com.boole.common.domain.Role;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created on 11/12/2015.
 */
@Transactional
public class CrewRepositoryUnitTest extends AbstractBooleCommonTest {

    @Autowired
    CrewRepository crewRepository;

    @Test
    public void testThatWeCanFindOneUsingRepository() {
        assertThat(crewRepository).isNotNull();
        Crew crew = crewRepository.findOne(1L);
        assertThat(crew).isNotNull();
        assertThat(crew.getName()).isNotNull()
                .isNotEmpty()
                .isEqualToIgnoringCase("Arthur Hotaling");
        System.out.println(crew.toString());
    }

    @Test
    public void testFindCrewAndLoadRoles(){
        assertThat(crewRepository).isNotNull();
        Crew crew = crewRepository.findOne(1L);
        assertThat(crew).isNotNull();

        Set<Role> roles = crew.getRoles();
        assertThat(roles).isNotNull()
                .isNotEmpty();
        assertThat(roles.size()).isGreaterThan(1);
        System.out.println("Number of For Crew: " + roles.size());
        roles.forEach(System.out::println);
    }
}
