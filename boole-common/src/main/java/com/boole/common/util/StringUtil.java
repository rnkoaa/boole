package com.boole.common.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created on 11/3/2015.
 */
public class StringUtil {

    private static final Character[] defaultChars = new Character[]{',', '.', ':', ';'};

    private static final Set<Character> defaultNonWordCharacters = new HashSet<>(defaultChars.length);

    static {
        defaultNonWordCharacters.addAll(Arrays.asList(defaultChars));
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    private static boolean isWhiteSpace(Character character) {
        return character == ' ' || character == '\n' || character == '\t';
    }

    public static String capitalize(String myName) {
        myName = myName.toLowerCase();
        String results = "";
        char[] resultChars = myName.toCharArray();
        Character character = Character.toUpperCase(myName.charAt(0));
        resultChars[0] = character;
        results = String.valueOf(resultChars);
        return results;
    }

    public static String capitalizeEachWord(String sentence) {
        List<String> newWords = Arrays.asList(sentence.split("\\s+"));
        return newWords.stream()
                .map(StringUtil::capitalize)
                .collect(Collectors.joining(" "));
    }

    public static String cropWholeWords(String value, int length) {
        return cropWholeWords(value, length, null);
    }

    /**
     * Converted c# method to java method from
     * <a href="http://joelabrahamsson.com/c-method-for-cropping-text-without-breaking-words/">
     *     http://joelabrahamsson.com/</a>
     * @param value
     * @param length
     * @param nonWordCharacters
     * @return
     */
    public static String cropWholeWords(String value, int length, Set<Character> nonWordCharacters) {
        if (isNullOrEmpty(value))
            throw new IllegalArgumentException("input parameter cannot be empty or null");

        if (nonWordCharacters == null || nonWordCharacters.size() == 0) {
            nonWordCharacters = defaultNonWordCharacters;
        }

        if (length >= value.length()) {
            return value;
        }

        char[] valueArray = value.toCharArray();

        int end = length;
        for (int i = end; i > 0; i--) {
            if (isWhiteSpace(valueArray[i])) {
                break;
            }

            if (nonWordCharacters.contains(valueArray[i])
                    && (valueArray.length == i + 1 || valueArray[i + 1] == ' ')) {
                //Removing a character that isn't whitespace but not part
                //of the word either (ie ".") given that the character is
                //followed by whitespace or the end of the string makes it
                //possible to include the word, so we do that.
                break;
            }
            end--;
        }

        if (end == 0) {
            //If the first word is longer than the length we favor
            //returning it as cropped over returning nothing at all.
            end = length;
        }

        value = String.valueOf(valueArray);
        return value.substring(0, end);
    }
}
