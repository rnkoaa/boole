package com.boole.common.repository;

import com.boole.common.AbstractBooleCommonTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created on 11/12/2015.
 */
public class CrewRepositoryUnitTest extends AbstractBooleCommonTest {

    @Autowired
    CrewRepository crewRepository;

    @Test
    public void testAutowired() {
        assertThat(crewRepository).isNotNull();
    }
}
