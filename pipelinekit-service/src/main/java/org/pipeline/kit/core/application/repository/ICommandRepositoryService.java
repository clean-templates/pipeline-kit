package org.pipeline.kit.core.application.repository;

import org.pipeline.kit.core.application.repository.dto.CreateBranchRequest;
import org.pipeline.kit.core.application.repository.dto.CreatePullRequest;
import org.pipeline.kit.core.application.repository.dto.CreateRepositoryRequest;
import org.pipeline.kit.core.domain.repository.Repository;

public interface ICommandRepositoryService {
    void createRepository(CreateRepositoryRequest from) throws Exception;

    Repository getRepositoryContent(String repoName, String repoBranch);

    void createBranch(CreateBranchRequest createBranchRequest);

    void createPullRequest(CreatePullRequest createPullRequest);
}
