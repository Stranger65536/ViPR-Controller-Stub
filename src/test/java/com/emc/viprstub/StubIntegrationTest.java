/*
 * Copyright 1994-2018 EMC Corporation. All rights reserved.
 */
package com.emc.viprstub;

import com.emc.vipr.client.ClientConfig;
import com.emc.vipr.client.ViPRCoreClient;
import com.emc.viprstub.config.ViPRStub;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ViPRStub.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class StubIntegrationTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(StubIntegrationTest.class);

    private final ViPRCoreClient client;

    public StubIntegrationTest() {
        client = new ViPRCoreClient(new ClientConfig()
                .withHost("localhost")
                .withConnectionTimeout(1000)
                .withIgnoringCertificates(true)
                .withMaxConcurrentTaskRequests(3)
                .withRequestLoggingEnabled()
                .withMaxRetries(3)
                .withMediaType("application/json"));
    }

    @Test
    public void testLogin() {
        final String token = client.auth().login("admin", "yep");
        LOGGER.info("Token: {}", token);
        assertThat(token, not(isEmptyString()));
    }
}


