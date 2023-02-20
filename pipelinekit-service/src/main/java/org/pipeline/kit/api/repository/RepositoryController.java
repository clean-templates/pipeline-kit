package org.pipeline.kit.api.repository;

import lombok.AllArgsConstructor;
import org.pipeline.kit.core.application.repository.ICommandRepositoryService;
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
        commandRepositoryService.createRepository(From(createRepositoryApiRequest));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Repository> getRepositoryContent(String owner, String repoName, String repoBranch) {
        Repository repositoryContent = commandRepositoryService.getRepositoryContent(owner.concat("/").concat(repoName), repoBranch);
        return ResponseEntity.ok(repositoryContent);
    }

    private CreateRepositoryRequest From(CreateRepositoryApiRequest createRepositoryApiRequest) {
        return CreateRepositoryRequest.builder()
                .name(createRepositoryApiRequest.getName())
                .build();
    }
}
