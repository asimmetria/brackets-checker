package ru.altunin.bracketschecker.service.abstracts;

import java.util.Map;

/**
 * An interface for providing configuration settings to the application obtained from various external sources.
 */
public interface ConfigurationProvider {

    /**
     * Retrieves a mapping of opening brackets to their corresponding closing brackets.
     *
     * @return A map containing pairs of opening and closing brackets.
     */
    Map<Character, Character> getBracketsPairs();

    /**
     * Retrieves a regular expression pattern representing valid characters inside the brackets in the configuration.
     *
     * @return A regular expression pattern for valid characters inside brackets.
     */
    String getRegex();

    /**
     * Indicates whether the use of inner brackets is considered useful in the text.
     *
     * @return true if inner brackets are considered useful, false otherwise.
     */
    boolean isInnerBracketsUseful();
}
