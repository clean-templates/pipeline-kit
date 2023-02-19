package org.pipeline.kit.core.application.github.repository.details;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Tree {
    private String path;
    private String mode;
    private String type;
    private String sha;
    private String url;
    private int size;
}
