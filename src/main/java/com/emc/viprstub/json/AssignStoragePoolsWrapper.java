package com.emc.viprstub.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssignStoragePoolsWrapper {
    @JsonProperty("storage_pool")
    private List<StoragePool> storagePools = new ArrayList<>();

    public List<StoragePool> getStoragePools() {
        return storagePools;
    }

    public void setStoragePools(List<StoragePool> storagePools) {
        this.storagePools = storagePools;
    }
}
