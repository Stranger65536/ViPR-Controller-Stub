package com.emc.viprstub.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 *         Created on 01.10.2014.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("itl")
public class ITL {

    @JsonProperty("hlu")
    private Integer hostLogicalUnitNumber;

    @JsonProperty("initiator")
    private Initiator initiator;

    public Integer getHostLogicalUnitNumber() {
        return hostLogicalUnitNumber;
    }

    public void setHostLogicalUnitNumber(Integer hostLogicalUnitNumber) {
        this.hostLogicalUnitNumber = hostLogicalUnitNumber;
    }

    public Initiator getInitiator() {
        return initiator;
    }

    public void setInitiator(Initiator initiator) {
        this.initiator = initiator;
    }
}
