package org.zero.memberservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zero.adminservice.config.FeatureToggle;

@RestController
@RequiredArgsConstructor
public class DebugController {

    private final FeatureToggle featureToggle;

    @GetMapping("/debug/feature")
    public String feature() {
        return featureToggle.getValue();
    }
}

