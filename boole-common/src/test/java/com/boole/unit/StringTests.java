package com.sakila.unit;

import com.sakila.util.StringUtil;
import org.junit.Test;

import java.util.HashSet;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/7/15.
 */
public class StringTests {
    private static final String sentence = "I often need to crop a " +
            "longer text down to a shorter version. That in it self is easy. However, " +
            "it's often nice to make it less obvious that " +
            "the text has been shortened by ensuring that it consists of whole words. ";

    @Test
    public void breakWords(){
        assertThat(1+1).isEqualTo(2);
        String croppedWord = StringUtil.cropWholeWords(sentence, 100);
        System.out.println(croppedWord);
    }
}
