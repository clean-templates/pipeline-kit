package org.pipeline.kit.core.domain.provider;

import org.kohsuke.github.GHCreateRepositoryBuilder;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import java.io.IOException;

public class GitHubProvider implements Provider {

    private GitHub gitHub;

    public GitHubProvider(String token) throws IOException {
        this.gitHub = new GitHubBuilder()
                .withOAuthToken(token)
                .build();

    }

    @Override
    public void createRepository(String name) throws IOException {
        GHCreateRepositoryBuilder repository = gitHub.createRepository(name);
        repository.done();
    }
}
