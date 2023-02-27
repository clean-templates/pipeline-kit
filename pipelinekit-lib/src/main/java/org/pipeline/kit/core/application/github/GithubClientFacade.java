package org.pipeline.kit.core.application.github;

import org.pipeline.kit.core.application.github.client.GithubApiClient;
import org.pipeline.kit.core.application.github.client.IGithubApiClient;
import org.pipeline.kit.core.domain.provider.PullRequestDetails;
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

    public void createBranch(String repositoryName, String branchName, String headCommit) throws IOException, InterruptedException {
        githubApiClient.createBranch(repositoryName, branchName, headCommit);
    }

    public String getBranchHeadCommit(String repositoryName, String branchName) throws IOException, InterruptedException {
        return githubApiClient.getBranchHeadCommit(repositoryName, branchName);
    }

    public void createPullRequest(PullRequestDetails pullRequestDetails) throws IOException, InterruptedException {
        githubApiClient.createPullRequest(pullRequestDetails);
    }
}