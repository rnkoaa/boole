package com.sakila;

import com.sakila.config.SakilaApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/5/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SakilaApplication.class)
public abstract class SakilaSpringTests {
}
