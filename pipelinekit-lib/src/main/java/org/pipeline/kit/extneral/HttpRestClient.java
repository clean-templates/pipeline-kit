package org.pipeline.kit.extneral;

import org.pipeline.kit.core.application.RestClient;
import org.pipeline.kit.core.domain.provider.PullRequestDetails;
import org.pipeline.kit.core.domain.repository.Repository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpRestClient implements RestClient {

    // TODO: externalize/Refactor httpclient requests
    // TODO: Write end-to-end tests

    private static final String HTTPS_API_GITHUB_COM_REPOS = "https://api.github.com/repos/";
    private HttpClient httpClient;

    public HttpRestClient() {
        this.httpClient = HttpClient.newHttpClient();

    }

    @Override
    public String getRepoDetails(String repoName, String branch) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(HTTPS_API_GITHUB_COM_REPOS
                        .concat(repoName)
                        .concat("/git/refs/heads/")
                        .concat(branch)))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    @Override
    public String getRepoContent(String token, Repository repoDetails) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(HTTPS_API_GITHUB_COM_REPOS
                        .concat(repoDetails.getName())
                        .concat("/git/trees/")
                        .concat(repoDetails.getHeadCommit())
                        .concat("?recursive=true")
                ))
                .header("Accept", "application/vnd.github+json")
                .header("Authorization", "Bearer " + token)
                .header("X-GitHub-Api-Version", "2022-11-28")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    @Override
    public void createBranch(String repositoryName, String branchName, String headCommit, String token) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(HTTPS_API_GITHUB_COM_REPOS
                        .concat(repositoryName)
                        .concat("/git/refs")
                ))
                .header("Accept", "application/vnd.github+json")
                .header("Authorization", "Bearer " + token)
                .header("X-GitHub-Api-Version", "2022-11-28")
                .POST(HttpRequest.BodyPublishers.ofString("{\"ref\":\"refs/heads/" + branchName + "\",\"sha\":\"" + headCommit + "\"}"))
                .build();

        httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    // TODO: create mappers
    @Override
    public void createPullRequest(PullRequestDetails pullRequestDetails, String token) throws IOException, InterruptedException {
        String postBody = "{\"title\":\"" + pullRequestDetails.getTitle() + "\",\"body\":\"" + pullRequestDetails.getBody() + "\",\"head\":\"" + pullRequestDetails.getSource() + "\",\"base\":\"" + pullRequestDetails.getDestination() + "\"}";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(HTTPS_API_GITHUB_COM_REPOS
                        .concat(pullRequestDetails.getRepositoryName())
                        .concat("pulls")
                ))
                .header("Accept", "application/vnd.github+json")
                .header("Authorization", "Bearer " + token)
                .header("X-GitHub-Api-Version", "2022-11-28")
                .POST(HttpRequest.BodyPublishers.ofString(postBody))
                .build();

        httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}