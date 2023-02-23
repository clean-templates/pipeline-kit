package org.pipeline.kit.api.repository;

import lombok.AllArgsConstructor;
import org.pipeline.kit.core.application.repository.ICommandRepositoryService;
import org.pipeline.kit.core.application.repository.dto.CreateBranchRequest;
import org.pipeline.kit.core.application.repository.dto.CreateRepositoryRequest;
import org.pipeline.kit.core.domain.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RepositoryController implements RepositoryApi{

    private ICommandRepositoryService commandRepositoryService;
    
  
    @Override
    public ResponseEntity<Void> createRepository(CreateRepositoryApiRequest createRepositoryApiRequest) throws Exception {
        commandRepositoryService.createRepository(from(createRepositoryApiRequest));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Repository> getRepositoryContent(String owner, String repoName, String repoBranch) {
        // TODO: Query Repository Service
        Repository repositoryContent = commandRepositoryService.getRepositoryContent(owner.concat("/").concat(repoName), repoBranch);
        return ResponseEntity.ok(repositoryContent);
    }

    @Override
    public ResponseEntity<Void> createBranch(CreateBranchApiRequest createBranchApiRequest) {
        commandRepositoryService.createBranch(from(createBranchApiRequest));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private CreateBranchRequest from(CreateBranchApiRequest createBranchApiRequest) {
        return CreateBranchRequest.builder()
                .branchName(createBranchApiRequest.getBranchName())
                .repository(createBranchApiRequest.getRepository())
                .build();
    }

    private CreateRepositoryRequest from(CreateRepositoryApiRequest createRepositoryApiRequest) {
        return CreateRepositoryRequest.builder()
                .name(createRepositoryApiRequest.getName())
                .build();
    }
}
