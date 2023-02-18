package org.pipeline.kit.api.repository;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/repository")
public interface RepositoryApi {

    @PostMapping("/create")
    @Operation(summary = "create a repository based on a pre-selected token with a specified name")
    ResponseEntity<Void> createRepository(@RequestBody CreateRepositoryApiRequest createRepositoryApiRequest) throws Exception;
}
