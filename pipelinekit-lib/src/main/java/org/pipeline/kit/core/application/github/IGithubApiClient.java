package org.pipeline.kit.core.application.github;

import org.kohsuke.github.GHRepository;
import org.pipeline.kit.core.domain.repository.Repository;

import java.io.IOException;
import java.net.URI;

public interface IGithubApiClient {
    URI buildUri(GHRepository repository, String branch);

    Repository getRepoDetails(String repositoryName, String repositoryBranch) throws IOException, InterruptedException;

    Repository getRepoContent(Repository repoDetails) throws IOException, InterruptedException;
}
