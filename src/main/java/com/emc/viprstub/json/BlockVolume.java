package com.emc.viprstub.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.List;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 *         Created on 01.10.2014.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BlockVolume {

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private String id;

    @JsonProperty("link")
    private Link link;

    @JsonProperty("invactive")
    private Boolean inactive;

    @JsonProperty("global")
    private Boolean global;

    @JsonProperty("remote")
    private Boolean remote;

    @JsonProperty("tags")
    private List<String> tags;

    @JsonProperty("internal")
    private Boolean internal;

    @JsonProperty("wwn")
    private String wwn;

    @JsonProperty("protocols")
    private List<String> protocols;

    @JsonProperty("project")
    private ViprProject viprProject;

    //TODO add protection

    @JsonProperty("storage_controller")
    private String storageSystemId;

    @JsonProperty("varray")
    private VirtualArray virtualArray;

    @JsonProperty("device_label")
    private String deviceLabel;

    @JsonProperty("native_id")
    private String nativeId;

    @JsonProperty("provisioned_capacity_gb")
    private String provisionedCapacityGb;

    @JsonProperty("allocated_capacity_gb")
    private String allocatedCapacityGb;

    @JsonProperty("requested_capacity_gb")
    private String requestedCapacityGb;

    @JsonProperty("pre_allocation_size_gb")
    private String preAllocatedSizeGb;

    @JsonProperty("is_composite")
    private Boolean composite;

    @JsonProperty("thinly_provisioned")
    private Boolean thinlyProvisioned;

    @JsonProperty("high_availability_backing_volumes")
    private List<Link> highAvailabilityBackingVolumes;

    @JsonProperty("access_state")
    private String accessState;

    @JsonProperty("storage_pool")
    private StoragePool storagePool;

    @JsonProperty("vpool")
    private VirtualPool virtualPool;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Boolean getInactive() {
        return inactive;
    }

    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    public Boolean getGlobal() {
        return global;
    }

    public void setGlobal(Boolean global) {
        this.global = global;
    }

    public Boolean getRemote() {
        return remote;
    }

    public void setRemote(Boolean remote) {
        this.remote = remote;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Boolean getInternal() {
        return internal;
    }

    public void setInternal(Boolean internal) {
        this.internal = internal;
    }

    public String getWwn() {
        return wwn;
    }

    public void setWwn(String wwn) {
        this.wwn = wwn;
    }

    public List<String> getProtocols() {
        return protocols;
    }

    public void setProtocols(List<String> protocols) {
        this.protocols = protocols;
    }

    public ViprProject getViprProject() {
        return viprProject;
    }

    public void setViprProject(ViprProject viprProject) {
        this.viprProject = viprProject;
    }

    public String getStorageSystemId() {
        return storageSystemId;
    }

    public void setStorageSystemId(String storageSystemId) {
        this.storageSystemId = storageSystemId;
    }

    public VirtualArray getVirtualArray() {
        return virtualArray;
    }

    public void setVirtualArray(VirtualArray virtualArray) {
        this.virtualArray = virtualArray;
    }

    public String getDeviceLabel() {
        return deviceLabel;
    }

    public void setDeviceLabel(String deviceLabel) {
        this.deviceLabel = deviceLabel;
    }

    public String getNativeId() {
        return nativeId;
    }

    public void setNativeId(String nativeId) {
        this.nativeId = nativeId;
    }

    public String getProvisionedCapacityGb() {
        return provisionedCapacityGb;
    }

    public void setProvisionedCapacityGb(String provisionedCapacityGb) {
        this.provisionedCapacityGb = provisionedCapacityGb;
    }

    public String getAllocatedCapacityGb() {
        return allocatedCapacityGb;
    }

    public void setAllocatedCapacityGb(String allocatedCapacityGb) {
        this.allocatedCapacityGb = allocatedCapacityGb;
    }

    public String getRequestedCapacityGb() {
        return requestedCapacityGb;
    }

    public void setRequestedCapacityGb(String requestedCapacityGb) {
        this.requestedCapacityGb = requestedCapacityGb;
    }

    public String getPreAllocatedSizeGb() {
        return preAllocatedSizeGb;
    }

    public void setPreAllocatedSizeGb(String preAllocatedSizeGb) {
        this.preAllocatedSizeGb = preAllocatedSizeGb;
    }

    public Boolean getComposite() {
        return composite;
    }

    public void setComposite(Boolean composite) {
        this.composite = composite;
    }

    public Boolean getThinlyProvisioned() {
        return thinlyProvisioned;
    }

    public void setThinlyProvisioned(Boolean thinlyProvisioned) {
        this.thinlyProvisioned = thinlyProvisioned;
    }

    public List<Link> getHighAvailabilityBackingVolumes() {
        return highAvailabilityBackingVolumes;
    }

    public void setHighAvailabilityBackingVolumes(List<Link> highAvailabilityBackingVolumes) {
        this.highAvailabilityBackingVolumes = highAvailabilityBackingVolumes;
    }

    public String getAccessState() {
        return accessState;
    }

    public void setAccessState(String accessState) {
        this.accessState = accessState;
    }

    public StoragePool getStoragePool() {
        return storagePool;
    }

    public void setStoragePool(StoragePool storagePool) {
        this.storagePool = storagePool;
    }

    public VirtualPool getVirtualPool() {
        return virtualPool;
    }

    public void setVirtualPool(VirtualPool virtualPool) {
        this.virtualPool = virtualPool;
    }
}
