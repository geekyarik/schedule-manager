package com.ystan.schedule;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(DatabaseModuleConfig.class)
@Configuration
@EntityScan(basePackages = PrincipalModuleConfig.BASE_PACKAGE)
public class PrincipalModuleConfig {

    /**
     * Base package of the principal module.
     */
    public static final String BASE_PACKAGE = "com.ystan.schedule";
}
