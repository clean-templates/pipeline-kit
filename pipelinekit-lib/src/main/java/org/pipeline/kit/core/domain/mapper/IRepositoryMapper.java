package org.pipeline.kit.core.domain.mapper;

import org.kohsuke.github.GHTree;
import org.pipeline.kit.core.domain.repository.Repository;

public interface IRepositoryMapper<Tree> {
    Repository mapToRepo(Tree tree);
}
