package org.pipeline.kit.core.application;

import org.pipeline.kit.core.application.github.GithubApiClient;
import org.pipeline.kit.core.application.github.IGithubApiClient;
import org.pipeline.kit.core.domain.repository.Repository;

import java.io.IOException;

public class GithubClientFacade {


    private IGithubApiClient githubApiClient;

    public GithubClientFacade(String token) {
        githubApiClient = new GithubApiClient(token);
    }

    public Repository getRepoContent(String repositoryName, String branch) throws IOException, InterruptedException {
        Repository repoDetails = githubApiClient.getRepoDetails(repositoryName, branch);
        githubApiClient.getRepoContent(repoDetails);
        return null;
    }
}
