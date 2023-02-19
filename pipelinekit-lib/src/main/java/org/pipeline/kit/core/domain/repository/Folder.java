package org.pipeline.kit.core.domain.repository;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
@Builder
public class Folder {
    private String name;
    private String id;
    private HashMap<String, Folder> subFolders;
    private HashMap<String, File> files;
}
