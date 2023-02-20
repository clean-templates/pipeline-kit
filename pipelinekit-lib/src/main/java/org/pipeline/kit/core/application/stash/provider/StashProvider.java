package org.pipeline.kit.core.application.stash.provider;

import org.pipeline.kit.core.domain.provider.Provider;
import org.pipeline.kit.core.domain.repository.Repository;

import java.io.IOException;

public class StashProvider implements Provider {

    private final String username;
    private final String password;

    public StashProvider(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void createRepository(String name) throws IOException {
        // TODO
    }

    @Override
    public Repository getRepoContent(String repositoryName, String branch) throws IOException, InterruptedException {
        return null;
    }
}
