package com.emc.viprstub.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 *         Created on 23.09.2014.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VirtualPoolContinuousCopiesInfo {

    @JsonProperty(value = "max_native_continuous_copies")
    private Integer maximumNumberOfContinuousCopies;

    @JsonProperty(value = "protection_mirror_vpool")
    private String virtualPoolForProtectionMirrorsId;

    public Integer getMaximumNumberOfContinuousCopies() {
        return maximumNumberOfContinuousCopies;
    }

    public void setMaximumNumberOfContinuousCopies(Integer maximumNumberOfContinuousCopies) {
        this.maximumNumberOfContinuousCopies = maximumNumberOfContinuousCopies;
    }

    public String getVirtualPoolForProtectionMirrorsId() {
        return virtualPoolForProtectionMirrorsId;
    }

    public void setVirtualPoolForProtectionMirrorsId(String virtualPoolForProtectionMirrorsId) {
        this.virtualPoolForProtectionMirrorsId = virtualPoolForProtectionMirrorsId;
    }
}
