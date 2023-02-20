package org.pipeline.kit.core.application.github;

import org.pipeline.kit.core.application.github.client.GithubApiClient;
import org.pipeline.kit.core.application.github.client.IGithubApiClient;
import org.pipeline.kit.core.domain.repository.Repository;

import java.io.IOException;

public class GithubClientFacade {


    private IGithubApiClient githubApiClient;

    public GithubClientFacade(String token) {
        githubApiClient = new GithubApiClient(token);
    }

    public Repository getRepoContent(String repositoryName, String branch) throws IOException, InterruptedException {
        Repository repoDetails = githubApiClient.getRepoDetails(repositoryName, branch);
        Repository repoContent = githubApiClient.getRepoContent(repoDetails);
        return repoContent;
    }
}
