package org.pipeline.kit.api.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePullRequestApiRequest {
    private String repositoryName;
    private String title;
    private String body;
    private String source;
    private String destination;
}
