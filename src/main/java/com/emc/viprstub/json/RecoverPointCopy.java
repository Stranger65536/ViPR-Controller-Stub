package com.emc.viprstub.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 *         Created on 23.09.2014.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecoverPointCopy {
    @JsonProperty(value = "varray")
    private String virtualArrayId;

    @JsonProperty(value = "vpool")
    private String virtualPoolId;

    @JsonProperty(value = "policy")
    private Policy policy;

    public String getVirtualArrayId() {
        return virtualArrayId;
    }

    public void setVirtualArrayId(String virtualArrayId) {
        this.virtualArrayId = virtualArrayId;
    }

    public String getVirtualPoolId() {
        return virtualPoolId;
    }

    public void setVirtualPoolId(String virtualPoolId) {
        this.virtualPoolId = virtualPoolId;
    }

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Policy {
        @JsonProperty(value = "journal_size")
        private String journalSize;
    }
}
