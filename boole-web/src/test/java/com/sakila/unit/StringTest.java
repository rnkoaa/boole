package com.boole.unit;

import com.boole.common.util.StringUtil;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 10/31/15.
 */
public class StringTest {

    @Test
    public void testCapitalize() {
        String allCaps = "WARREN";

        //System.out.println(allCaps.toLowerCase().replac);
        assertThat(StringUtil.capitalize(allCaps)).isEqualTo("Warren");
    }

    @Test
    public void testCapitalizeSentence() {
        String sentence = "ACADEMY DINOSAUR";

        assertThat(StringUtil.capitalizeEachWord(sentence)).isEqualTo("Academy Dinosaur");
    }
}
