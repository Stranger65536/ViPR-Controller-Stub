/*
 * Copyright 1994-2018 EMC Corporation. All rights reserved.
 */
package com.emc.viprstub;

import com.emc.storageos.model.vpool.BlockVirtualPoolParam;
import com.emc.storageos.model.vpool.BlockVirtualPoolRestRep;
import com.emc.vipr.client.ClientConfig;
import com.emc.vipr.client.ViPRCoreClient;
import com.emc.viprstub.config.ViPRStub;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@DirtiesContext
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ViPRStub.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class StubCreatePoolIntegrationTest {
    private final ViPRCoreClient client;

    public StubCreatePoolIntegrationTest() {
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
    public void testVirtualPoolCreate() {
        final BlockVirtualPoolParam param = TestUtils.prepareVirtualPoolParams(client, "test");
        final URI poolId = client.blockVpools().create(param).getId();
        final List<BlockVirtualPoolRestRep> pools = client.blockVpools().getAll();
        assertThat(pools.stream().map(BlockVirtualPoolRestRep::getId)
                        .map(URI::toString)
                        .collect(Collectors.toList()),
                equalTo(singletonList(poolId.toString())));
    }
}


