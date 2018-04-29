package com.emc.viprstub;

import com.emc.storageos.model.BulkIdParam;
import com.emc.storageos.model.varray.VirtualArrayList;

public interface ViPRStubService {
    VirtualArrayList getVarrays();

    VirtualArrayList getVarrays(final BulkIdParam ids);
}
