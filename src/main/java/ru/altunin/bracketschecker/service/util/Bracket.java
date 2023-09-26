package ru.altunin.bracketschecker.service.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bracket {
    private Character value;
    private int index;
    private boolean useful;
    private int counterInnerUsefulPairsBrackets;

    public void incrementCounter() {
        counterInnerUsefulPairsBrackets++;
    }
}