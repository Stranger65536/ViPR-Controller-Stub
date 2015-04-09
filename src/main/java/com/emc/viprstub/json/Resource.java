package com.emc.viprstub.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 * @author vladislav.trofimov@emc.com
 * @date 18.09.2014
 */
@JsonRootName("resource")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Resource {

    @JsonProperty("name")
    private String resourceName;

    @JsonProperty("id")
    private String resourceId;

    @JsonProperty("link")
    private Link resourceLink;

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Link getResourceLink() {
        return resourceLink;
    }

    public void setResourceLink(Link resourceLink) {
        this.resourceLink = resourceLink;
    }
}
