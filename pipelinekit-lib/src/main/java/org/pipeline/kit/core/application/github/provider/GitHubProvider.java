package org.pipeline.kit.core.application.github.provider;

import org.kohsuke.github.GHCreateRepositoryBuilder;
import org.kohsuke.github.GHTree;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.pipeline.kit.core.application.github.GithubClientFacade;
import org.pipeline.kit.core.application.mapper.GithubRepositoryMapper;
import org.pipeline.kit.core.application.mapper.IRepositoryMapper;
import org.pipeline.kit.core.domain.provider.Provider;
import org.pipeline.kit.core.domain.provider.PullRequestDetails;
import org.pipeline.kit.core.domain.repository.Repository;

import java.io.IOException;

public class GitHubProvider implements Provider {

    private final GitHub gitHub;
    private final IRepositoryMapper<GHTree> repositoryMapper;
    private final GithubClientFacade githubClientFacade;


    public GitHubProvider(String token) throws IOException {
        this.gitHub = new GitHubBuilder()
                .withOAuthToken(token)
                .build();
        this.repositoryMapper = new GithubRepositoryMapper();
        this.githubClientFacade = new GithubClientFacade(token);
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
     * @return Repository Object containing its structure
     * @throws IOException
     */
    @Override
    public Repository getRepoContent(String repositoryName, String branch) throws IOException, InterruptedException {
        return githubClientFacade.getRepoContent(repositoryName, branch);
    }

    @Override
    public void createBranch(String repositoryName, String branchName) throws IOException, InterruptedException {
        // TODO: ability to branch from different branches
        String headCommit = githubClientFacade.getBranchHeadCommit(repositoryName, "master");
        githubClientFacade.createBranch(repositoryName, branchName, headCommit);
    }

    @Override
    public void createPullRequest(PullRequestDetails pullRequestDetails) throws IOException, InterruptedException {
        githubClientFacade.createPullRequest(pullRequestDetails);
    }


}