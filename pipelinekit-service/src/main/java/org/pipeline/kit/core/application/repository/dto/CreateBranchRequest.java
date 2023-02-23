package org.pipeline.kit.core.application.repository.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateBranchRequest {
    private String repository;
    private String branchName;
}
