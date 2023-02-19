package org.pipeline.kit.extneral;

import org.pipeline.kit.core.application.RestClient;
import org.pipeline.kit.core.domain.repository.Repository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpRestClient implements RestClient {

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
}