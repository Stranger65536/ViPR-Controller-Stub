package com.emc.viprstub.json;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 */
public class GetExportsByVolumeRequest {

    @JsonProperty("id")
    private List<String> volumeIds;

    public List<String> getVolumeIds() {
        return volumeIds;
    }

    public void setVolumeIds(List<String> volumeIds) {
        this.volumeIds = volumeIds;
    }
}
