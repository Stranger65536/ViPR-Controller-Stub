package com.emc.viprstub.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 *         Created on 8/27/2014.
 */
@Configuration
@EnableAsync
@ComponentScan({"com.emc.viprstub"})
@PropertySource(value = "classpath:app.properties")
@Import({MongoConfig.class})
public class ApplicationConfig {
}
