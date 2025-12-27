package org.zero.adminservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@RefreshScope
@Component
public class FeatureToggle {
    @Value("${admin.property}")
    private String value;

    public String getValue() { return value; }
}