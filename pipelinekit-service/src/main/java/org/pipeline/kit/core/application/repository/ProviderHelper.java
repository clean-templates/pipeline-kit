package org.pipeline.kit.core.application.repository;

import org.pipeline.kit.core.application.ProviderFactory;
import org.pipeline.kit.core.domain.exceptions.ProviderIsNotSupportedException;
import org.pipeline.kit.core.domain.provider.Provider;
import org.pipeline.kit.core.domain.provider.ProviderType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class ProviderHelper {

    @Value("${access.token.github}")
    private String token;


    public Provider getProvider() throws IOException, ProviderIsNotSupportedException {
        return new ProviderFactory().get(ProviderType.GITHUB, token);
    }
}
