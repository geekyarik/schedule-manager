package com.ystan.schedule;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Database config aggregator.
 *
 * @author ystan
 */
@Import(ModelsModuleConfig.class)
@Configuration
@EntityScan(basePackages = DatabaseModuleConfig.BASE_PACKAGE)
@ComponentScan(basePackages = DatabaseModuleConfig.BASE_PACKAGE)
@EnableJpaRepositories(basePackages = DatabaseModuleConfig.BASE_PACKAGE)
@PropertySource(value = DatabaseModuleConfig.DATABASE_PROPERTIES_SOURCE)
public class DatabaseModuleConfig {

    /**
     * Base package of the DB module.
     */
    public static final String BASE_PACKAGE = "com.ystan.schedule";

    /**
     * Path to the .properties file with DB configuration.
     */
    public static final String DATABASE_PROPERTIES_SOURCE = "classpath:database.properties";
}
