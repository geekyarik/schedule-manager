package com.ystan.schedule;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = ModelsModuleConfig.BASE_PACKAGE)
public class ModelsModuleConfig {

    /**
     * Base package of the DB module.
     */
    public static final String BASE_PACKAGE = "com.ystan.schedule";
}
