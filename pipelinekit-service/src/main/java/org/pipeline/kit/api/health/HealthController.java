package org.pipeline.kit.api.health;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController implements HealthApi{


    @Override
    public ResponseEntity<String> getHealth() {
        return ResponseEntity.ok("UP");
    }
}
