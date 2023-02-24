package org.pipeline.kit.core.domain.provider;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PullRequestDetails {
    private String repositoryName;
    private String source;
    private String destination;
    private String title;
    private String body;
}
