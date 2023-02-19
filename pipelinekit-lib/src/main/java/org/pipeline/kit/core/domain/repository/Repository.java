package org.pipeline.kit.core.domain.repository;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Repository {
    private String url;
    private String sha;
    private boolean isTruncated;

    private List<Folder> folderList;
    private List<File> fileList;
}
