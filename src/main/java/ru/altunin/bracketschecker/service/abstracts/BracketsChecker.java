package ru.altunin.bracketschecker.service.abstracts;

/**
 * Interface for checking the correctness of bracket placement in a text.
 */
public interface BracketsChecker {

    /**
     * Checks the text for the correctness of bracket placement.
     *
     * @param text The text to be checked.
     * @return true if the bracket placement is correct, otherwise false.
     */
    boolean check(String text);
}
