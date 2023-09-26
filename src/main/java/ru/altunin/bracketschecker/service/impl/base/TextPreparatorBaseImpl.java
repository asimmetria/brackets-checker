package ru.altunin.bracketschecker.service.impl.base;

import ru.altunin.bracketschecker.exception.PreparationTextError;
import ru.altunin.bracketschecker.service.abstracts.ConfigurationProvider;

import java.util.Map;
/**
 * This abstract class provides a base implementation of the TextPreparator interface.
 * It depends on a ConfigurationProvider object that supplies the necessary properties
 * for text preparation for validation.
 */
public abstract class TextPreparatorBaseImpl {

    private final ConfigurationProvider configurationProvider;

    /**
     * Constructs a TextPreparatorBaseImpl instance with the specified ConfigurationProvider.
     *
     * @param configurationProvider The ConfigurationProvider that supplies properties for text preparation.
     */
    public TextPreparatorBaseImpl(ConfigurationProvider configurationProvider) {
        this.configurationProvider = configurationProvider;
    }

    /**
     * Sanitizes the provided text by removing unnecessary characters or content based on the configuration
     * provided by the ConfigurationProvider.
     *
     * @param text The text to be prepared for validation.
     * @return The sanitized text suitable for validation.
     * @throws PreparationTextError If an error occurs during text preparation.
     */
    public String sanitizeText(String text) throws PreparationTextError {
        if (text == null) {
            throw new PreparationTextError("The text is null!");
        }

        if (text.trim().isEmpty()) {
            throw new PreparationTextError("The text is empty!");
        }

        Map<Character, Character> bracketsPairs = configurationProvider.getBracketsPairs();
        String regex = configurationProvider.getRegex();

        StringBuilder regexBuilder = new StringBuilder(regex.substring(0, regex.length() - 1));
        for (Map.Entry<Character, Character> entry : bracketsPairs.entrySet()) {
            regexBuilder.append('\\').append(entry.getKey());
            regexBuilder.append('\\').append(entry.getValue());
        }
        text = text.replaceAll(regexBuilder.append(']').toString(), "");

        if (text.isEmpty()) {
            throw new PreparationTextError("There are no useful characters in the text!");
        }

        return text;
    }

}
