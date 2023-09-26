package ru.altunin.bracketschecker.service.impl.first;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.altunin.bracketschecker.service.abstracts.BracketsChecker;
import ru.altunin.bracketschecker.service.abstracts.ConfigurationProvider;
import ru.altunin.bracketschecker.service.abstracts.TextPreparator;
import ru.altunin.bracketschecker.service.impl.base.BracketsCheckerBaseImpl;

@Service
@Qualifier("firstImpl")
public class BracketsCheckerImpl extends BracketsCheckerBaseImpl implements BracketsChecker {

    public BracketsCheckerImpl(@Qualifier("firstImpl") ConfigurationProvider configurationProvider,
                               @Qualifier("firstImpl") TextPreparator preparation) {
        super(configurationProvider, preparation);
    }

}
