package com.emc.viprstub.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 *         Created on 23.09.2014.
 */

//TODO add other fields
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecoverPoint {
    @JsonProperty(value = "copies")
    private List<RecoverPointCopy> copies;

    @JsonProperty(value = "rpo_value")
    private Long rpoValue;

    @JsonProperty(value = "rpo_type")
    private String rpoType; //TODO refactor to enum

    public List<RecoverPointCopy> getCopies() {
        return copies;
    }

    public void setCopies(List<RecoverPointCopy> copies) {
        this.copies = copies;
    }

    public Long getRpoValue() {
        return rpoValue;
    }

    public void setRpoValue(Long rpoValue) {
        this.rpoValue = rpoValue;
    }

    public String getRpoType() {
        return rpoType;
    }

    public void setRpoType(String rpoType) {
        this.rpoType = rpoType;
    }
}
