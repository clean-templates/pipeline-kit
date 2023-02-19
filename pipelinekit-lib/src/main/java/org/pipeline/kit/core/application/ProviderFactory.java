package org.pipeline.kit.core.application;

import org.pipeline.kit.core.domain.exceptions.ProviderIsNotSupportedException;
import org.pipeline.kit.core.domain.provider.Provider;
import org.pipeline.kit.core.domain.provider.ProviderType;

import java.io.IOException;

public class ProviderFactory  implements IProviderFactory {

    @Override
    public Provider get(ProviderType providerType, String token) throws ProviderIsNotSupportedException, IOException {
        if(providerType.equals(ProviderType.GITHUB))
            return new GitHubProvider(token);
        else
            throw new ProviderIsNotSupportedException("No Such Provider");
    }
}
