package com.emc.viprstub.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 *         Created on 23.09.2014.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VirtualPoolSnapshotsInfo {

    @JsonProperty(value = "max_native_snapshots")
    private Integer maximumNativeSnapshots;

    public Integer getMaximumNativeSnapshots() {
        return maximumNativeSnapshots;
    }

    public void setMaximumNativeSnapshots(Integer maximumNativeSnapshots) {
        this.maximumNativeSnapshots = maximumNativeSnapshots;
    }
}