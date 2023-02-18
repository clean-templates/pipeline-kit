package org.pipeline.kit.core.domain.provider;

import org.pipeline.kit.core.domain.exceptions.ProviderIsNotSupportedException;

import java.io.IOException;

public interface IProviderFactory {

    Provider get(ProviderType providerType, String token) throws ProviderIsNotSupportedException, IOException;
}
