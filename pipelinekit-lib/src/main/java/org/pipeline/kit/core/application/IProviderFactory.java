package org.pipeline.kit.core.application;

import org.pipeline.kit.core.domain.exceptions.ProviderIsNotSupportedException;
import org.pipeline.kit.core.domain.provider.Provider;
import org.pipeline.kit.core.domain.provider.ProviderType;

import java.io.IOException;

public interface IProviderFactory {

    Provider get(ProviderType providerType, String token) throws ProviderIsNotSupportedException, IOException;
}
