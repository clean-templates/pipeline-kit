package org.pipeline.kit.core.domain.provider;

import org.kohsuke.github.*;
import org.pipeline.kit.core.domain.mapper.GithubRepositoryMapper;
import org.pipeline.kit.core.domain.mapper.IRepositoryMapper;
import org.pipeline.kit.core.domain.repository.Repository;

import java.io.IOException;

public class GitHubProvider implements Provider {

    private final GitHub gitHub;
    private final IRepositoryMapper<GHTree> repositoryMapper;

    public GitHubProvider(String token) throws IOException {
        this.gitHub = new GitHubBuilder()
                .withOAuthToken(token)
                .build();
        this.repositoryMapper = new GithubRepositoryMapper();
    }

    @Override
    public void createRepository(String name) throws IOException {
        GHCreateRepositoryBuilder repository = gitHub.createRepository(name);
        repository.done();

    }

    /***
     *
     * @param repositoryName should of the format owner/repo-name
     * @param branch
     * @param depth recursive calls to get the depth of the tree
     * @return Repository Object containing its structure
     * @throws IOException
     */
    @Override
    public Repository getRepoContent(String repositoryName, String branch, int depth) throws IOException {
        GHRepository repository = gitHub.getRepository(repositoryName);
        String branchSha = repository.getBranch(branch).getSHA1();
        GHTree treeRecursive = repository.getTreeRecursive(branchSha, depth);
        return repositoryMapper.mapToRepo(treeRecursive);
    }

}