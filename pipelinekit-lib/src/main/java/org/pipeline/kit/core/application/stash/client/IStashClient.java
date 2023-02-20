package org.pipeline.kit.core.application.stash.client;

import org.pipeline.kit.core.domain.repository.Repository;

import java.io.IOException;

public interface IStashClient {


    Repository getRepoContent(Repository repoDetails) throws IOException, InterruptedException;
}
