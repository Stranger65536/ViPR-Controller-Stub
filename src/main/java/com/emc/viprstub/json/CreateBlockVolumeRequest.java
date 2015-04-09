package com.emc.viprstub.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author vladislav.trofimov@emc.com
 * @date 18.09.2014
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateBlockVolumeRequest {

    @JsonProperty("consistency_group")
    private String consistencyGroup;

    @JsonProperty("count")
    private Integer countOfVolumes;

    @JsonProperty("name")
    private String nameOfVolume;

    @JsonProperty("project")
    private String projectId;

    @JsonProperty("size")
    private String sizeOfVolume;

    @JsonProperty("varray")
    private String virtualArrayId;

    @JsonProperty("vpool")
    private String virtualPoolId;

    public String getConsistencyGroup() {
        return consistencyGroup;
    }

    public void setConsistencyGroup(String consistencyGroup) {
        this.consistencyGroup = consistencyGroup;
    }

    public Integer getCountOfVolumes() {
        return countOfVolumes;
    }

    public void setCountOfVolumes(Integer countOfVolumes) {
        this.countOfVolumes = countOfVolumes;
    }

    public String getNameOfVolume() {
        return nameOfVolume;
    }

    public void setNameOfVolume(String nameOfVolume) {
        this.nameOfVolume = nameOfVolume;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getSizeOfVolume() {
        return sizeOfVolume;
    }

    public void setSizeOfVolume(String sizeOfVolume) {
        this.sizeOfVolume = sizeOfVolume;
    }

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
}