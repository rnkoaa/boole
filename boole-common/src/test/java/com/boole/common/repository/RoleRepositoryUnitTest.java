package com.boole.common.repository;

import com.boole.common.AbstractBooleCommonTest;
import com.boole.common.domain.Role;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.boole.common.repository.specifications.RoleSpecifications.actorRolesForMovie;
import static com.boole.common.repository.specifications.RoleSpecifications.roleWithCrew;
import static com.boole.common.repository.specifications.RoleSpecifications.rolesForMovie;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/14/15.
 */
public class RoleRepositoryUnitTest extends AbstractBooleCommonTest {
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void findRolesForMovie() {
        List<Role> roles = roleRepository.findAll(actorRolesForMovie(8L));

        assertThat(roles).isNotEmpty();
        assertThat(roles.size()).isGreaterThan(1).isEqualTo(4);
    }

    @Test
    public void findDirectorRolesForMovie() {
        List<Role> roles = roleRepository.findAll(rolesForMovie(8L, "Director"));

        assertThat(roles).isNotEmpty();
        assertThat(roles.size()).isEqualTo(1);
    }

    @Test
    public void findActorRolesForMovie() {
        List<Role> roles = roleRepository.findAll(rolesForMovie(8L, "Cast"));

        assertThat(roles).isNotEmpty();
        assertThat(roles.size()).isGreaterThan(1)
                .isEqualTo(4);
    }

    @Test
    public void findWriterRolesForMovie() {
        List<Role> roles = roleRepository.findAll(rolesForMovie(8L, "Writer"));

        assertThat(roles).isNotEmpty();
        assertThat(roles.size()).isEqualTo(1);
    }

    @Test
    public void findProducerRolesForMovie() {
        List<Role> roles = roleRepository.findAll(rolesForMovie(8L, "Producer"));

        assertThat(roles).isEmpty();
        assertThat(roles.size()).isEqualTo(0);
    }

    @Test
    public void findAllRolesForMovie() {
        List<Role> roles = roleRepository.findAll(rolesForMovie(8L));

        assertThat(roles).isNotEmpty();
        assertThat(roles.size()).isGreaterThan(0).isEqualTo(6);
    }

    @Test
    public void findCrewUsingRoles(){
        List<Role> roles = roleRepository.findAll(roleWithCrew(9L));
        assertThat(roles).isNotEmpty();
    }
}
