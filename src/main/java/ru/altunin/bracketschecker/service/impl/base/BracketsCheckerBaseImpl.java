package ru.altunin.bracketschecker.service.impl.base;

import lombok.extern.log4j.Log4j;
import ru.altunin.bracketschecker.service.abstracts.ConfigurationProvider;
import ru.altunin.bracketschecker.service.abstracts.TextPreparator;
import ru.altunin.bracketschecker.service.util.Bracket;

import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This abstract class provides a base implementation of the BracketChecker interface.
 * It has dependencies on two objects: ConfigurationProvider, which supplies properties
 * for the checking process, and TextPreparator, which prepares text for validation.
 */
@Log4j
public abstract class BracketsCheckerBaseImpl {

    private final ConfigurationProvider configurationProvider;
    private final TextPreparator preparator;

    /**
     * Constructs a BracketsCheckerBaseImpl instance with the specified ConfigurationProvider
     * and TextPreparator dependencies.
     *
     * @param configurationProvider The ConfigurationProvider that supplies properties for validation.
     * @param preparator            The TextPreparator that prepares text for validation.
     */
    public BracketsCheckerBaseImpl(ConfigurationProvider configurationProvider, TextPreparator preparator) {
        this.configurationProvider = configurationProvider;
        this.preparator = preparator;
    }

    /**
     * Checks the provided text for correct bracket placement.
     *
     * @param text The text to be checked for correct bracket placement.
     * @return true if the bracket placement is correct, otherwise false.
     */

    public boolean check(String text) {
        log.debug("Method \"check\" received the text: \"" + text + "\"");
        text = preparator.sanitizeText(text);
        log.debug("Text after preparation: \"" + text + "\"");

        Map<Character, Character> bracketsPairs = configurationProvider.getBracketsPairs();
        boolean innerBracketsUseful = configurationProvider.isInnerBracketsUseful();
        LinkedList<Bracket> stack = new LinkedList<>();
        Bracket bracketWasDeletedLastTime = new Bracket();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (bracketsPairs.containsValue(ch)) {
                stack.add(new Bracket(ch, i, false, 0));
            } else if (bracketsPairs.containsKey(ch)) {
                if (stack.isEmpty()) {
                    log.debug("Closing bracket without opening! Index in the prepared text [" + i + "]\n" +
                            "Checking is complete. The text is incorrect\n");
                    return false;
                }

                Bracket lastBracket = stack.removeLast();

                if (!lastBracket.isUseful()
                        && lastBracket.getCounterInnerUsefulPairsBrackets() < 2
                        && lastBracket.getValue() == bracketWasDeletedLastTime.getValue()) {
                    log.debug("Double identical brackets! Indexes in the prepared text [" + lastBracket.getIndex() + "] [" + i + "]\n" +
                            "Checking is complete. The text is incorrect\n");
                    return false;
                }

                if (!stack.isEmpty() && innerBracketsUseful) {
                    lastBracket.setUseful(true);
                    stack.getLast().incrementCounter();
                }

                if (lastBracket.getValue() != bracketsPairs.get(ch)) {
                    log.debug("The closing character does not match the opening one. Index in the prepared text [" + i + "]\n" +
                            "Checking is complete. The text is incorrect\n");
                    return false;
                }


                if (bracketWasDeletedLastTime.getValue() != null
                        && !bracketWasDeletedLastTime.isUseful()
                        && (bracketWasDeletedLastTime.getIndex() - lastBracket.getIndex() == 1)
                        && (bracketWasDeletedLastTime.getValue() == lastBracket.getValue())) {
                    log.debug("Double identical brackets! Indexes in the prepared text [" + lastBracket.getIndex() + "] [" + i + "]\n" +
                            "Checking is complete. The text is incorrect\n");
                    return false;
                }

                if (!innerBracketsUseful) {
                    if (bracketWasDeletedLastTime.getValue() != null
                            && !lastBracket.isUseful()) {
                        log.debug("There is no useful characters between brackets. Indexes in the prepared text [" +
                                lastBracket.getIndex() + "] [" + i + "] ====> innerBracketsUseful == " + innerBracketsUseful +
                                "\nChecking is complete. The text is incorrect\n");
                        return false;
                    }
                }

                if (i - lastBracket.getIndex() == 1) {
                    log.debug("There are no useful characters between brackets. Indexes in the prepared text [" +
                            lastBracket.getIndex() + "] [" + i + "]\n" +
                            "Checking is complete. The text is incorrect\n");
                    return false;
                }

                bracketWasDeletedLastTime = lastBracket;

            } else if (!stack.isEmpty()) {
                stack.getLast().setUseful(true);
            }
        }

        if (!stack.isEmpty()) {
            log.debug("There is(are) no closing bracket(s)! Index(es) opening bracket(s) in the prepared text: [" +
                    stack.stream()
                            .map(e -> String.valueOf(e.getIndex()))
                            .collect(Collectors.joining(", ")) + "]");
            log.debug("Checking is complete. The text is incorrect!\n");
            return false;
        } else {
            log.debug("Checking is complete. The text is correct!\n");
            return true;
        }
    }
}
