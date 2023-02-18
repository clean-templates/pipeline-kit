package org.pipeline.kit.core.application.repository;

import lombok.AllArgsConstructor;
import org.pipeline.kit.core.application.repository.dto.CreateRepositoryRequest;
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
}
