/*
 * Copyright 1994-2018 EMC Corporation. All rights reserved.
 */
package com.emc.viprstub;

import com.emc.storageos.model.pools.StoragePoolRestRep;
import com.emc.storageos.model.systems.StorageSystemRestRep;
import com.emc.storageos.model.varray.VirtualArrayRestRep;
import com.emc.storageos.model.vpool.BlockVirtualPoolParam;
import com.emc.storageos.model.vpool.BlockVirtualPoolRestRep;
import com.emc.storageos.model.vpool.VirtualPoolPoolUpdateParam;
import com.emc.vipr.client.ClientConfig;
import com.emc.vipr.client.ViPRCoreClient;
import com.emc.viprstub.config.ViPRStub;
import com.emc.viprstub.service.PropertiesResolver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ViPRStub.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class StubIntegrationTest {
    @Autowired
    private PropertiesResolver propertiesResolver;

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

    @Test
    @SuppressWarnings("JUnitTestMethodWithNoAssertions")
    public void testLogout() {
        client.auth().forceLogout();
    }

    @Test
    public void testGetAllStoragePools() {
        final List<StoragePoolRestRep> pools = client.storagePools().getAll();
    }

    @Test
    public void testGetStoragePoolById() {
        final URI poolUri = null;
        final StoragePoolRestRep pool = client.storagePools().get(poolUri);
    }

    @Test
    public void testGetStorageSystemById() {
        final URI systemUri = null;
        final StorageSystemRestRep systemRestRep = client.storageSystems().get(systemUri);
    }

    @Test
    public void testGetAllVirtualPools() {
        final List<BlockVirtualPoolRestRep> pools = client.blockVpools().getAll();
    }

    @Test
    public void testCreateVirtualPool() {
        final BlockVirtualPoolParam param = null;
        final URI poolId = client.blockVpools().create(param).getId();
    }

    @Test
    public void testAssignStoragePoolsToVPool() {
        final URI poolId = null;
        final VirtualPoolPoolUpdateParam param = null;
        client.blockVpools().assignStoragePools(poolId, param);
    }

    @Test
    public void testGetVirtualArrays() {
        final List<VirtualArrayRestRep> vArrays = client.varrays().getAll();
        assertThat(vArrays.stream().map(VirtualArrayRestRep::getId)
                        .map(URI::toString)
                        .collect(Collectors.toList()),
                equalTo(singletonList(propertiesResolver.resolve(
                        "urn:storageos:VirtualDataCenter:${vdcid}:vdc1"))));
    }
}


