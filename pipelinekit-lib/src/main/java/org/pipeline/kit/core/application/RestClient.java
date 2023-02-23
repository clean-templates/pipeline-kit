package org.pipeline.kit.core.application;

import org.pipeline.kit.core.domain.repository.Repository;

import java.io.IOException;

public interface RestClient {

    String getRepoDetails(String repoName, String branch) throws IOException, InterruptedException;

    String getRepoContent(String token, Repository repoDetails) throws IOException, InterruptedException;

    void createBranch(String repositoryName, String branchName, String headCommit, String token) throws IOException, InterruptedException;
}
