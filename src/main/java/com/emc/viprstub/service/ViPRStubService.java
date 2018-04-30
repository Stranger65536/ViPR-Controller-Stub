package com.emc.viprstub.service;

import com.emc.storageos.model.BulkIdParam;
import com.emc.storageos.model.pools.StoragePoolBulkRep;
import com.emc.storageos.model.pools.StoragePoolList;
import com.emc.storageos.model.pools.StoragePoolRestRep;
import com.emc.storageos.model.systems.StorageSystemRestRep;
import com.emc.storageos.model.varray.VirtualArrayList;
import com.emc.storageos.model.vpool.BlockVirtualPoolBulkRep;
import com.emc.storageos.model.vpool.BlockVirtualPoolParam;
import com.emc.storageos.model.vpool.BlockVirtualPoolRestRep;
import com.emc.storageos.model.vpool.VirtualPoolList;

import java.io.IOException;
import java.net.URISyntaxException;

public interface ViPRStubService {
    VirtualArrayList getVarrays();

    VirtualArrayList getVarrays(final BulkIdParam ids);

    StoragePoolList getStoragePools();

    StoragePoolBulkRep getStoragePools(BulkIdParam ids);

    StoragePoolRestRep getStoragePool(String id) throws URISyntaxException;

    StorageSystemRestRep getStorageSystem(String id) throws URISyntaxException;

    VirtualPoolList getVirtualPools();

    BlockVirtualPoolRestRep createVirtualPool(BlockVirtualPoolParam param) throws IOException;

    BlockVirtualPoolBulkRep getVirtualPools(BulkIdParam ids);
}
