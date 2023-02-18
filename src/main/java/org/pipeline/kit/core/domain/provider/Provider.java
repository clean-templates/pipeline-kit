package org.pipeline.kit.core.domain.provider;

import java.io.IOException;

public interface Provider {

    void createRepository(String name) throws IOException;

}
