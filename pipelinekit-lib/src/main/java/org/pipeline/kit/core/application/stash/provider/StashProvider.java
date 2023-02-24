package org.pipeline.kit.core.application.stash.provider;

import org.pipeline.kit.core.domain.provider.Provider;
import org.pipeline.kit.core.domain.provider.PullRequestDetails;
import org.pipeline.kit.core.domain.repository.Repository;

import java.io.IOException;

public class StashProvider implements Provider {

   private final String token;

    public StashProvider(String token) {
        this.token = token;
    }

    @Override
    public void createRepository(String name) throws IOException {
        // TODO
        new RuntimeException("Not implemented yet");
    }

    @Override
    public Repository getRepoContent(String repositoryName, String branch) throws IOException, InterruptedException {
        return null;
    }

    @Override
    public void createBranch(String repositoryName, String branchName) {
        new RuntimeException("Not implemented yet");
    }

    @Override
    public void createPullRequest(PullRequestDetails pullRequestDetails) {

    }


}
