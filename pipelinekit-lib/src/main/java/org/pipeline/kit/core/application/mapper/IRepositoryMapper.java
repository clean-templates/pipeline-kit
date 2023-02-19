package org.pipeline.kit.core.application.mapper;

import org.pipeline.kit.core.domain.repository.Repository;

public interface IRepositoryMapper<Tree> {
    Repository mapToRepo(Tree tree);
}
