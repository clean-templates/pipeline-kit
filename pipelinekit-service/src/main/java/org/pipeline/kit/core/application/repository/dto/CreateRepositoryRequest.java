package org.pipeline.kit.core.application.repository.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateRepositoryRequest {
    private String name;
}
