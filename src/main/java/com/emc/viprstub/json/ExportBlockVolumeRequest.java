package com.emc.viprstub.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 *         Created on 26.03.2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExportBlockVolumeRequest {

    @JsonProperty("initiators")
    private List<String> initiatorIds = new ArrayList<>();

    @JsonProperty("name")
    private String name;

    @JsonProperty("project")
    private String projectId;

    @JsonProperty("type")
    private String exportType;

    @JsonProperty("varray")
    private String virtualArrayId;

    @JsonProperty("volumes")
    private List<BlockVolume> volumes;

    public List<BlockVolume> getVolumes() {
        return volumes;
    }

    public void setVolumes(List<BlockVolume> volumes) {
        this.volumes = volumes;
    }

    public String getVirtualArrayId() {
        return virtualArrayId;
    }

    public void setVirtualArrayId(String virtualArrayId) {
        this.virtualArrayId = virtualArrayId;
    }

    public String getExportType() {
        return exportType;
    }

    public void setExportType(String exportType) {
        this.exportType = exportType;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getInitiatorIds() {
        return initiatorIds;
    }

    public void setInitiatorIds(List<String> initiatorIds) {
        this.initiatorIds = initiatorIds;
    }
}
