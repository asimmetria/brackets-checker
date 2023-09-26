package ru.altunin.bracketschecker.service.impl.first;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.altunin.bracketschecker.service.abstracts.ConfigurationProvider;
import ru.altunin.bracketschecker.service.impl.base.ConfigurationProviderBaseImpl;

@Service
@Qualifier("firstImpl")
public class ConfigurationProviderImpl extends ConfigurationProviderBaseImpl implements ConfigurationProvider {
}