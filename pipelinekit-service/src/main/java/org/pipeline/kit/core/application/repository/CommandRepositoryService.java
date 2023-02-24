package org.pipeline.kit.core.application.repository;

import lombok.AllArgsConstructor;
import org.pipeline.kit.core.application.repository.dto.CreateBranchRequest;
import org.pipeline.kit.core.application.repository.dto.CreatePullRequest;
import org.pipeline.kit.core.application.repository.dto.CreateRepositoryRequest;
import org.pipeline.kit.core.domain.provider.PullRequestDetails;
import org.pipeline.kit.core.domain.repository.Repository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommandRepositoryService implements ICommandRepositoryService {

    private ProviderHelper providerHelper;

    @Override
    public void createRepository(CreateRepositoryRequest request) {
        try {
            providerHelper.getProvider().createRepository(request.getName());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Repository getRepositoryContent(String repoName, String repoBranch) {
        try {
            return providerHelper.getProvider().getRepoContent(repoName,repoBranch);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void createBranch(CreateBranchRequest createBranchRequest) {
        try {
            providerHelper.getProvider().createBranch(createBranchRequest.getRepository(), createBranchRequest.getBranchName());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void createPullRequest(CreatePullRequest createPullRequest) {
        try {
            providerHelper.getProvider().createPullRequest(from(createPullRequest));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private PullRequestDetails from(CreatePullRequest createPullRequest) {
        return PullRequestDetails.builder()
                .body(createPullRequest.getBody())
                .title(createPullRequest.getTitle())
                .repositoryName(createPullRequest.getRepositoryName())
                .destination(createPullRequest.getDestination())
                .source(createPullRequest.getSource())
                .build();
    }
}
