package com.boole;

import org.elasticsearch.index.IndexService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/5/15.
 */
public class StoreIndexTestsWeb extends booleWebApplicationTests {

    @Autowired
    IndexService indexService;

    @Test
    public void testAutowire(){
        assertThat(indexService).isNotNull();
    }
}
