/*
 * Copyright 1994-2018 EMC Corporation. All rights reserved.
 */
package com.emc.viprstub.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.emc.viprstub")
@SuppressWarnings({"UtilityClassCanBeEnum", "NonFinalUtilityClass"})
public class ViPRStub {
    public static void main(final String[] args) {
        SpringApplication.run(ViPRStub.class);
    }
}
