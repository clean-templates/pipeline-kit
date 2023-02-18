package org.pipeline.kit.api.health;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/health")
public interface HealthApi {

    @Operation(summary = "service health endpoint")
    @GetMapping
    ResponseEntity<String> getHealth();
}
