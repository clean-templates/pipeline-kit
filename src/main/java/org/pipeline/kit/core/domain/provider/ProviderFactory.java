package org.pipeline.kit.core.domain.provider;

import org.pipeline.kit.core.domain.exceptions.ProviderIsNotSupportedException;

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
