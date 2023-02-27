package org.pipeline.kit.core.application.mapper;

import org.kohsuke.github.GHTree;
import org.kohsuke.github.GHTreeEntry;
import org.pipeline.kit.core.domain.repository.Folder;
import org.pipeline.kit.core.domain.repository.Repository;

import java.util.List;

public class GithubRepositoryMapper implements IRepositoryMapper<GHTree> {

    @Override
    public Repository mapToRepo(GHTree ghTree) {
        return Repository.builder()
                .isTruncated(ghTree.isTruncated())
                .headCommit(ghTree.getSha())
                .url(ghTree.getUrl().toString())
//                .folderList(mapFolder(ghTree.getEntry("/")))
                .build();
    }

    private List<Folder> mapFolder(GHTreeEntry entry) {
        entry.getUrl();
        return null;
    }
}