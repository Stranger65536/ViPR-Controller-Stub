package com.emc.viprstub.service;

import com.emc.storageos.model.BulkIdParam;
import com.emc.storageos.model.pools.StoragePoolBulkRep;
import com.emc.storageos.model.pools.StoragePoolList;
import com.emc.storageos.model.pools.StoragePoolRestRep;
import com.emc.storageos.model.varray.VirtualArrayList;

import java.net.URISyntaxException;

public interface ViPRStubService {
    VirtualArrayList getVarrays();

    VirtualArrayList getVarrays(final BulkIdParam ids);

    StoragePoolList getStoragePools();

    StoragePoolBulkRep getStoragePools(BulkIdParam ids);

    StoragePoolRestRep getStoragePool(String id) throws URISyntaxException;
}
