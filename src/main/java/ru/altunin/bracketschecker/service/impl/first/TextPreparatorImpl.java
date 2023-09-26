package ru.altunin.bracketschecker.service.impl.first;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.altunin.bracketschecker.service.abstracts.ConfigurationProvider;
import ru.altunin.bracketschecker.service.abstracts.TextPreparator;
import ru.altunin.bracketschecker.service.impl.base.TextPreparatorBaseImpl;

@Service
@Qualifier("firstImpl")
public class TextPreparatorImpl extends TextPreparatorBaseImpl implements TextPreparator {

    public TextPreparatorImpl(@Qualifier("firstImpl") ConfigurationProvider configurationProvider) {
        super(configurationProvider);
    }

}
