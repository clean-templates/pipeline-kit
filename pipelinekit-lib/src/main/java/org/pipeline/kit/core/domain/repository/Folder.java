package org.pipeline.kit.core.domain.repository;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Folder {
    private String name;
    private String id;
    private List<Folder> subFolders;
    private List<File> files;
}
