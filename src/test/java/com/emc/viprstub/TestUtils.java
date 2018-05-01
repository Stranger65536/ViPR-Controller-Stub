/*
 * Copyright 1994-2018 EMC Corporation. All rights reserved.
 */
package com.emc.viprstub;

import com.emc.storageos.model.vpool.BlockVirtualPoolParam;
import com.emc.storageos.model.vpool.StoragePoolAssignmentChanges;
import com.emc.storageos.model.vpool.StoragePoolAssignments;
import com.emc.storageos.model.vpool.VirtualPoolPoolUpdateParam;
import com.emc.vipr.client.ViPRCoreClient;

import java.net.URI;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

enum TestUtils {
    ;

    @SuppressWarnings("SameParameterValue")
    static BlockVirtualPoolParam prepareVirtualPoolParams(final ViPRCoreClient coreClient, final String name) {
        final BlockVirtualPoolParam poolParam = new BlockVirtualPoolParam();

        poolParam.setName(name);
        poolParam.setDescription("Created using CoprHD Smart Pool Manager");
        poolParam.setProvisionType("Thin");
        poolParam.setDriveType("SAS");
        poolParam.setMaxPaths(1);
        poolParam.setProtocols(new HashSet<>(singletonList("iSCSI")));
        poolParam.setUseMatchedPools(false);
        poolParam.setVarrays(getVArrays(coreClient));

        return poolParam;
    }

    static VirtualPoolPoolUpdateParam prepareAssignParams(final Collection<URI> pools) {
        final StoragePoolAssignments assignments = prepareAssignments(pools);
        final StoragePoolAssignmentChanges changes = prepareAssignmentChanges(assignments);
        return prepareUpdateParams(changes);
    }

    private static Set<String> getVArrays(final ViPRCoreClient client) {
        return client.varrays().getAll().stream().map(i -> i.getId().toString()).collect(Collectors.toSet());
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
