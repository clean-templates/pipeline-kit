package org.pipeline.kit.core.application.github.repository.details;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Root{
    private String ref;
    private String node_id;
    private String url;
    private Object object;
    private boolean isTruncated;
    private ArrayList<Tree> tree;

}
