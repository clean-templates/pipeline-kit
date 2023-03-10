package org.pipeline.kit.core.application.github.client;

import org.kohsuke.github.GHRepository;
import org.pipeline.kit.core.domain.provider.PullRequestDetails;
import org.pipeline.kit.core.domain.repository.Repository;

import java.io.IOException;
import java.net.URI;

public interface IGithubApiClient {

    Repository getRepoDetails(String repositoryName, String repositoryBranch) throws IOException, InterruptedException;

    Repository getRepoContent(Repository repoDetails) throws IOException, InterruptedException;

    void createBranch(String repositoryName, String branchName, String headCommit) throws IOException, InterruptedException;

    String getBranchHeadCommit(String repositoryName, String branchName) throws IOException, InterruptedException;

    void createPullRequest(PullRequestDetails pullRequestDetails) throws IOException, InterruptedException;
}
