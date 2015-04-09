package com.emc.viprstub.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 *         Created on 23.09.2014.
 */
//TODO add remote copies
@JsonIgnoreProperties(ignoreUnknown = true)
public class VirtualPoolProtection {

    @JsonProperty(value = "snapshots")
    private VirtualPoolSnapshotsInfo virtualPoolSnapshotsInfo;

    @JsonProperty(value = "continuous_copies")
    private VirtualPoolContinuousCopiesInfo virtualPoolContinuousCopiesInfo;

    @JsonProperty(value = "recoverpoint")
    private RecoverPoint recoverPoint;


    public VirtualPoolSnapshotsInfo getVirtualPoolSnapshotsInfo() {
        return virtualPoolSnapshotsInfo;
    }

    public void setVirtualPoolSnapshotsInfo(VirtualPoolSnapshotsInfo virtualPoolSnapshotsInfo) {
        this.virtualPoolSnapshotsInfo = virtualPoolSnapshotsInfo;
    }

    public VirtualPoolContinuousCopiesInfo getVirtualPoolContinuousCopiesInfo() {
        return virtualPoolContinuousCopiesInfo;
    }

    public void setVirtualPoolContinuousCopiesInfo(VirtualPoolContinuousCopiesInfo virtualPoolContinuousCopiesInfo) {
        this.virtualPoolContinuousCopiesInfo = virtualPoolContinuousCopiesInfo;
    }

    public RecoverPoint getRecoverPoint() {
        return recoverPoint;
    }

    public void setRecoverPoint(RecoverPoint recoverPoint) {
        this.recoverPoint = recoverPoint;
    }
}
