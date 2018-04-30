/*
 * Copyright 1994-2018 EMC Corporation. All rights reserved.
 */
package com.emc.viprstub;

import com.emc.storageos.model.RelatedResourceRep;
import com.emc.storageos.model.pools.StoragePoolRestRep;
import com.emc.storageos.model.systems.StorageSystemRestRep;
import com.emc.storageos.model.varray.VirtualArrayRestRep;
import com.emc.storageos.model.vpool.BlockVirtualPoolParam;
import com.emc.storageos.model.vpool.BlockVirtualPoolRestRep;
import com.emc.storageos.model.vpool.StoragePoolAssignmentChanges;
import com.emc.storageos.model.vpool.StoragePoolAssignments;
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
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ViPRStub.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class StubIntegrationTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(StubIntegrationTest.class);
    private final ViPRCoreClient client;
    @Autowired
    private PropertiesResolver propertiesResolver;

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
        assertThat(pools.stream().map(StoragePoolRestRep::getId)
                        .map(URI::toString)
                        .collect(Collectors.toList()),
                equalTo(asList(propertiesResolver.resolve("urn:storageos:StoragePool:${spid1}:vdc1"),
                        propertiesResolver.resolve("urn:storageos:StoragePool:${spid2}:vdc1"),
                        propertiesResolver.resolve("urn:storageos:StoragePool:${spid3}:vdc1"),
                        propertiesResolver.resolve("urn:storageos:StoragePool:${spid4}:vdc1"))));

    }

    @Test
    public void testGetStoragePoolById() throws URISyntaxException {
        final URI poolUri = new URI(propertiesResolver.resolve("urn:storageos:StoragePool:${spid1}:vdc1"));
        final StoragePoolRestRep pool = client.storagePools().get(poolUri);
        assertThat(pool.getName(), equalTo("CLARIION+FNM00104900081+POOL+U+Pool RAID10 (1)"));
    }

    @Test
    public void testGetStorageSystemById() throws URISyntaxException {
        final URI systemUri = new URI(propertiesResolver.resolve("urn:storageos:StorageSystem:${ssid1}:vdc1"));
        final StorageSystemRestRep system = client.storageSystems().get(systemUri);
        assertThat(system.getName(), equalTo("CLARIION+FNM00104900081"));
    }

    @Test
    public void testGetAllVirtualPools() {
        final List<BlockVirtualPoolRestRep> pools = client.blockVpools().getAll();
        assertThat(pools, is(empty()));
    }

    @Test
    public void testCreateVirtualPool() {
        final BlockVirtualPoolParam param = prepareVirtualPoolParams("test");
        final URI poolId = client.blockVpools().create(param).getId();
        final List<BlockVirtualPoolRestRep> pools = client.blockVpools().getAll();
        assertThat(pools.stream().map(BlockVirtualPoolRestRep::getId)
                        .map(URI::toString)
                        .collect(Collectors.toList()),
                equalTo(singletonList(poolId.toString())));
    }

    @Test
    public void testAssignStoragePoolsToVPool() throws URISyntaxException {
        final BlockVirtualPoolParam param = prepareVirtualPoolParams("test");
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

    @Test
    public void testGetVirtualArrays() {
        final List<VirtualArrayRestRep> vArrays = client.varrays().getAll();
        assertThat(vArrays.stream().map(VirtualArrayRestRep::getId)
                        .map(URI::toString)
                        .collect(Collectors.toList()),
                equalTo(singletonList(propertiesResolver.resolve(
                        "urn:storageos:VirtualDataCenter:${vdcid}:vdc1"))));
    }

    @SuppressWarnings("SameParameterValue")
    private BlockVirtualPoolParam prepareVirtualPoolParams(final String name) {
        final BlockVirtualPoolParam poolParam = new BlockVirtualPoolParam();

        poolParam.setName(name);
        poolParam.setDescription("Created using CoprHD Smart Pool Manager");
        poolParam.setProvisionType("Thin");
        poolParam.setDriveType("SAS");
        poolParam.setMaxPaths(1);
        poolParam.setProtocols(new HashSet<>(singletonList("iSCSI")));
        poolParam.setUseMatchedPools(false);
        poolParam.setVarrays(getVArrays());

        return poolParam;
    }

    private Set<String> getVArrays() {
        return client.varrays().getAll().stream().map(i -> i.getId().toString()).collect(Collectors.toSet());
    }

    private static VirtualPoolPoolUpdateParam prepareAssignParams(final Collection<URI> pools) {
        final StoragePoolAssignments assignments = prepareAssignments(pools);
        final StoragePoolAssignmentChanges changes = prepareAssignmentChanges(assignments);
        return prepareUpdateParams(changes);
    }

    private static StoragePoolAssignments prepareAssignments(final Collection<URI> pools) {
        final StoragePoolAssignments assignments = new StoragePoolAssignments();
        assignments.setStoragePools(pools.stream().map(URI::toString).collect(Collectors.toSet()));
        return assignments;
    }

    private static StoragePoolAssignmentChanges prepareAssignmentChanges(final StoragePoolAssignments assignments) {
        final StoragePoolAssignmentChanges changes = new StoragePoolAssignmentChanges();
        changes.setAdd(assignments);
        return changes;
    }

    private static VirtualPoolPoolUpdateParam prepareUpdateParams(final StoragePoolAssignmentChanges changes) {
        final VirtualPoolPoolUpdateParam updateParam = new VirtualPoolPoolUpdateParam();
        updateParam.setStoragePoolAssignmentChanges(changes);
        return updateParam;
    }
}


