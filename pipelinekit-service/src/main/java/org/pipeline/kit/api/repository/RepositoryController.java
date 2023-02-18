package org.pipeline.kit.api.repository;

import lombok.AllArgsConstructor;
import org.pipeline.kit.core.application.repository.ICommandRepositoryService;
import org.pipeline.kit.core.application.repository.dto.CreateRepositoryRequest;
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

    private CreateRepositoryRequest From(CreateRepositoryApiRequest createRepositoryApiRequest) {
        return CreateRepositoryRequest.builder()
                .name(createRepositoryApiRequest.getName())
                .build();
    }
}
