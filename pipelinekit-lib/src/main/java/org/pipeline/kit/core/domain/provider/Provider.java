package org.pipeline.kit.core.domain.provider;

import org.pipeline.kit.core.domain.repository.Repository;

import java.io.IOException;

public interface Provider {

    void createRepository(String name) throws IOException;

    Repository getRepoContent(String repositoryName ,String branch, int depth) throws IOException;

}
