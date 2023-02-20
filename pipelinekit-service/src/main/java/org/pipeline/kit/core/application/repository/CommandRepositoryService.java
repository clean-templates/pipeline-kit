package org.pipeline.kit.core.application.repository;

import lombok.AllArgsConstructor;
import org.pipeline.kit.core.application.repository.dto.CreateRepositoryRequest;
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
}
