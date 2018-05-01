/*
 * Copyright 1994-2018 EMC Corporation. All rights reserved.
 */
package com.emc.viprstub;

import com.emc.storageos.model.RelatedResourceRep;
import com.emc.storageos.model.vpool.BlockVirtualPoolParam;
import com.emc.storageos.model.vpool.BlockVirtualPoolRestRep;
import com.emc.storageos.model.vpool.VirtualPoolPoolUpdateParam;
import com.emc.vipr.client.ClientConfig;
import com.emc.vipr.client.ViPRCoreClient;
import com.emc.viprstub.config.ViPRStub;
import com.emc.viprstub.service.PropertiesResolver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static com.emc.viprstub.TestUtils.prepareAssignParams;
import static com.emc.viprstub.TestUtils.prepareVirtualPoolParams;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@DirtiesContext
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ViPRStub.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class StubAssignPoolsIntegrationTest {
    private final ViPRCoreClient client;

    @Autowired
    private PropertiesResolver propertiesResolver;

    public StubAssignPoolsIntegrationTest() {
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
    public void testStoragePoolsAssignToVPool() throws URISyntaxException {
        final BlockVirtualPoolParam param = prepareVirtualPoolParams(client, "test");
        final URI poolId = client.blockVpools().create(param).getId();
        final List<URI> spIds = singletonList(new URI(propertiesResolver.resolve(
                "urn:storageos:StoragePool:${spid1}:vdc1")));
        final VirtualPoolPoolUpdateParam updateParam = prepareAssignParams(spIds);
        final BlockVirtualPoolRestRep updated = client.blockVpools().assignStoragePools(poolId, updateParam);
        assertThat(updated.getAssignedStoragePools().stream()
                .map(RelatedResourceRep::getId).map(URI::toString).collect(Collectors.toList()), equalTo(
                singletonList(new URI(propertiesResolver.resolve(
                        "/vdc/storage-systems/urn:storageos:storage_system:${ssid1}:" +
                                "vdc1/storage-pools/urn:storageos:StoragePool:${spid1}:vdc1")).toString())));
    }
}


