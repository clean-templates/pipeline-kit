package org.pipeline.kit.api.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreateBranchApiRequest {
    private String repository;
    private String branchName;
}
