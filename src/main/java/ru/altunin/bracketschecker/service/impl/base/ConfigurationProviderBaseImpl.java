package ru.altunin.bracketschecker.service.impl.base;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This abstract class provides a base implementation of the ConfigurationProvider interface
 * and retrieves properties from the application.yml file. If properties cannot be retrieved
 * from the file, default values are used.
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "properties")
public abstract class ConfigurationProviderBaseImpl {
    /**
     * A mapping of closing brackets to their corresponding opening brackets.
     */
    private Map<Character, Character> bracketsPairs = new HashMap<>();

    /**
     * A regular expression pattern representing valid characters inside the brackets.
     * Default value is set to "[^a-zA-Z0-9а-яА-Я]".
     */
    @Value("${properties.regex:[^a-zA-Z0-9а-яА-Я]}")
    private String regex;

    /**
     * Indicates whether the use of inner brackets is considered useful.
     * Default value is set to "true".
     */
    @Value("${properties.is-inner-brackets-useful:true}")
    private boolean isInnerBracketsUseful;

    /**
     * A list of bracket pairs provided in the application.yml file.
     * Default value is set to ["(", ")", "[", "]", "{", "}"].
     */
    @Value("${properties.brackets:['(', ')', '[', ']', '{', '}']")
    private List<String> brackets;

    /**
     * Initializes the ConfigurationProvider based on properties from application.yml,
     * or uses default values if properties cannot be retrieved.
     * Throws a RuntimeException if the brackets list in application.yml is incorrect.
     */
    @PostConstruct
    public void init() {
        if (brackets.isEmpty()) {
            brackets = Arrays.asList("(", ")", "[", "]", "{", "}");
        }
        if (brackets.size() % 2 != 0) {
            throw new RuntimeException("Wrong brackets list in application.yml");
        }
        for (int i = brackets.size() - 1; i > 0; i -= 2) {
            bracketsPairs.put(brackets.get(i).charAt(0), brackets.get(i - 1).charAt(0));
        }
    }

    public boolean isInnerBracketsUseful() {
        return isInnerBracketsUseful;
    }

}
