package com.emc.viprstub.json;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 *         Created on 08.09.2014.
 */

public class ControllerParameter {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "value")
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
