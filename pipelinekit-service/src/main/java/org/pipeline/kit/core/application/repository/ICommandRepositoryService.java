package org.pipeline.kit.core.application.repository;

import org.pipeline.kit.core.application.repository.dto.CreateRepositoryRequest;

public interface ICommandRepositoryService {
    void createRepository(CreateRepositoryRequest from) throws Exception;
}
