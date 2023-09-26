package ru.altunin.bracketschecker.service.abstracts;

import ru.altunin.bracketschecker.exception.PreparationTextError;

/**
 * An interface for preparing text for validation by removing unuseful characters.
 */
public interface TextPreparator {

    /**
     * Sanitizes the provided text by removing unnecessary characters or content.
     *
     * @param text The text to be prepared for validation.
     * @return The sanitized text suitable for validation.
     * @throws PreparationTextError If an error occurs during text preparation.
     */
    String sanitizeText(String text) throws PreparationTextError;
}
