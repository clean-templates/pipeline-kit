package org.pipeline.kit.core.domain.repository;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
@Builder
public class Repository {
    private String url;

    private String name;
    private String branch;
    private String headCommit;
    private boolean isTruncated;

    private HashMap<String, Folder> folderList;
    private HashMap<String, File> fileList;
}
