package org.pipeline.kit.core.application.mapper;

import org.kohsuke.github.GHTree;
import org.pipeline.kit.core.domain.repository.Repository;

public interface IRepositoryMapper<Tree> {
    Repository mapToRepo(Tree tree);
}
