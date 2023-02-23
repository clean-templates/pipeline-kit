package org.pipeline.kit.api.repository;


import io.swagger.v3.oas.annotations.Operation;
import org.pipeline.kit.core.domain.repository.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/repository")
public interface RepositoryApi {

    @PostMapping("/create")
    @Operation(summary = "create a repository based on a pre-selected token with a specified name")
    ResponseEntity<Void> createRepository(@RequestBody CreateRepositoryApiRequest createRepositoryApiRequest) throws Exception;

    @GetMapping("/{owner}/{repoName}/{repoBranch}")
    @Operation(summary = "get repository directories")
    ResponseEntity<Repository> getRepositoryContent(
            @PathVariable String owner,
            @PathVariable String repoName,
            @PathVariable String repoBranch) throws Exception;

    @PostMapping("/create/branch")
    @Operation(summary = "get repository directories")
    ResponseEntity<Void> createBranch(@RequestBody CreateBranchApiRequest createBranchApiRequest);
}
