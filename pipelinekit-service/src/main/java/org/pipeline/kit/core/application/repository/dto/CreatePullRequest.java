package org.pipeline.kit.core.application.repository.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatePullRequest {
    private String repositoryName;
    private String title;
    private String body;
    private String source;
    private String destination;
}
