package org.pipeline.kit.core.application.github.repository.details;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Object{
    private String sha;
    private String type;
    private String url;
}