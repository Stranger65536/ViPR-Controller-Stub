package com.emc.viprstub.json;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 *         Created on 08.09.2014.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoragePool {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty
    private Link link;

    @JsonProperty(value = "inactive")
    private Boolean inactive;

    @JsonProperty(value = "tags")
    private List<String> tags = new ArrayList<>();

    @JsonProperty(value = "protocols")
    private List<Protocol> protocols = new ArrayList<>();

    @JsonProperty(value = "creation_time")
    private Long creationTime;

    @JsonProperty(value = "native_guid")
    private String nativeGuid;

    @JsonProperty(value = "controller_params")
    private List<ControllerParameter> controllerParameters = new ArrayList<>();

    @JsonProperty(value = "operational_status")
    private OperationalStatus operationalStatus;

    @JsonProperty(value = "registration_status")
    private RegistrationStatus registrationStatus;

    @JsonProperty(value = "usable_gb")
    private Long usableGb;

    @JsonProperty(value = "free_gb")
    private Long freeGb;

    @JsonProperty(value = "used_gb")
    private Long usedGb;

    @JsonProperty(value = "percent_used")
    private Long percentUsed;

    @JsonProperty(value = "subscribed_gb")
    private Long subscribedGb;

    @JsonProperty(value = "percent_subscribed")
    private Long percent_subscribed;

    @JsonProperty(value = "assigned_varrays")
    private List<String> assignedVirtualArrays = new ArrayList<>();

    @JsonProperty(value = "connected_varrays")
    private List<String> connectedVirtualArrays = new ArrayList<>();

    @JsonProperty(value = "tagged_varrays")
    private List<String> taggedVirtualArrays = new ArrayList<>();

    @JsonProperty(value = "max_resources")
    private Integer maximumStorageResources;

    @JsonProperty(value = "num_resources")
    private Integer numberOfStorageResources;

    @JsonProperty(value = "maximum_thin_volume_size_gb")
    private Long thinVolumeMaximumSizeGb;

    @JsonProperty(value = "minimum_thin_volume_size_gb")
    private Long thinVolumeMinimumSizeGb;

    @JsonProperty(value = "maximum_thick_volume_size_gb")
    private Long thickVolumeMaximumSizeGb;

    @JsonProperty(value = "minimum_thick_volume_size_gb")
    private Long thickVolumeMinimumSizeGb;

    @JsonProperty(value = "raid_levels")
    private List<String> raidLevels = new ArrayList<>();
    ; //TODO refactor into enum

    @JsonProperty(value = "drive_types")
    private List<String> driveTypes = new ArrayList<>();
    ;

    @JsonProperty(value = "copy_types")
    private List<String> copyTypes = new ArrayList<>();
    ;

    @JsonProperty(value = "tier_utilization_percentages")
    private List<TierUtilizationPercentage> tierUtilizationPercentages = new ArrayList<>();
    ;

    @JsonProperty(value = "pool_name")
    private String poolName;

    @JsonProperty(value = "pool_service_type")
    private PoolServiceType poolServiceType;

    @JsonProperty(value = "supported_resource_types")
    private VolumeResourceType volumeResourceType;

    @JsonProperty(value = "max_thin_pool_subscription_percentage")
    private Integer thinPoolMaximumSubscriptionPercentage;

    @JsonProperty(value = "max_pool_utilization_percentage")
    private Integer poolMaximumUtilizationPercentage;

    @JsonProperty(value = "thin_volume_preallocation_supported")
    private Boolean thinVolumePreallocationSupported;

    @JsonProperty(value = "storage_system")
    private StorageSystem storageSystem;

    @JsonIgnore
    public String getStorageSystemNameAndSerialNumber() {
        String[] parsedNativeGuid = nativeGuid.split("\\+");
        if (parsedNativeGuid.length != 5) {
            return null;
        }
        return parsedNativeGuid[0] + " " + parsedNativeGuid[1];
    }

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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Protocol> getProtocols() {
        return protocols;
    }

    public void setProtocols(List<Protocol> protocols) {
        this.protocols = protocols;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }

    public String getNativeGuid() {
        return nativeGuid;
    }

    public void setNativeGuid(String nativeGuid) {
        this.nativeGuid = nativeGuid;
    }

    public List<ControllerParameter> getControllerParameters() {
        return controllerParameters;
    }

    public void setControllerParameters(List<ControllerParameter> controllerParameters) {
        this.controllerParameters = controllerParameters;
    }

    public OperationalStatus getOperationalStatus() {
        return operationalStatus;
    }

    public void setOperationalStatus(OperationalStatus operationalStatus) {
        this.operationalStatus = operationalStatus;
    }

    public Long getUsableGb() {
        return usableGb;
    }

    public void setUsableGb(Long usableGb) {
        this.usableGb = usableGb;
    }

    public Long getFreeGb() {
        return freeGb;
    }

    public void setFreeGb(Long freeGb) {
        this.freeGb = freeGb;
    }

    public Long getUsedGb() {
        return usedGb;
    }

    public void setUsedGb(Long usedGb) {
        this.usedGb = usedGb;
    }

    public Long getPercentUsed() {
        return percentUsed;
    }

    public void setPercentUsed(Long percentUsed) {
        this.percentUsed = percentUsed;
    }

    public Long getSubscribedGb() {
        return subscribedGb;
    }

    public void setSubscribedGb(Long subscribedGb) {
        this.subscribedGb = subscribedGb;
    }

    public Long getPercent_subscribed() {
        return percent_subscribed;
    }

    public void setPercent_subscribed(Long percent_subscribed) {
        this.percent_subscribed = percent_subscribed;
    }

    public List<String> getAssignedVirtualArrays() {
        return assignedVirtualArrays;
    }

    public void setAssignedVirtualArrays(List<String> assignedVirtualArrays) {
        this.assignedVirtualArrays = assignedVirtualArrays;
    }

    public List<String> getConnectedVirtualArrays() {
        return connectedVirtualArrays;
    }

    public void setConnectedVirtualArrays(List<String> connectedVirtualArrays) {
        this.connectedVirtualArrays = connectedVirtualArrays;
    }

    public List<String> getTaggedVirtualArrays() {
        return taggedVirtualArrays;
    }

    public void setTaggedVirtualArrays(List<String> taggedVirtualArrays) {
        this.taggedVirtualArrays = taggedVirtualArrays;
    }

    public Integer getMaximumStorageResources() {
        return maximumStorageResources;
    }

    public void setMaximumStorageResources(Integer maximumStorageResources) {
        this.maximumStorageResources = maximumStorageResources;
    }

    public Integer getNumberOfStorageResources() {
        return numberOfStorageResources;
    }

    public void setNumberOfStorageResources(Integer numberOfStorageResources) {
        this.numberOfStorageResources = numberOfStorageResources;
    }

    public Long getThinVolumeMaximumSizeGb() {
        return thinVolumeMaximumSizeGb;
    }

    public void setThinVolumeMaximumSizeGb(Long thinVolumeMaximumSizeGb) {
        this.thinVolumeMaximumSizeGb = thinVolumeMaximumSizeGb;
    }

    public Long getThinVolumeMinimumSizeGb() {
        return thinVolumeMinimumSizeGb;
    }

    public void setThinVolumeMinimumSizeGb(Long thinVolumeMinimumSizeGb) {
        this.thinVolumeMinimumSizeGb = thinVolumeMinimumSizeGb;
    }

    public Long getThickVolumeMaximumSizeGb() {
        return thickVolumeMaximumSizeGb;
    }

    public void setThickVolumeMaximumSizeGb(Long thickVolumeMaximumSizeGb) {
        this.thickVolumeMaximumSizeGb = thickVolumeMaximumSizeGb;
    }

    public Long getThickVolumeMinimumSizeGb() {
        return thickVolumeMinimumSizeGb;
    }

    public void setThickVolumeMinimumSizeGb(Long thickVolumeMinimumSizeGb) {
        this.thickVolumeMinimumSizeGb = thickVolumeMinimumSizeGb;
    }

    public List<String> getRaidLevels() {
        return raidLevels;
    }

    public void setRaidLevels(List<String> raidLevels) {
        this.raidLevels = raidLevels;
    }

    public List<String> getDriveTypes() {
        return driveTypes;
    }

    public void setDriveTypes(List<String> driveTypes) {
        this.driveTypes = driveTypes;
    }

    public List<String> getCopyTypes() {
        return copyTypes;
    }

    public void setCopyTypes(List<String> copyTypes) {
        this.copyTypes = copyTypes;
    }

    public List<TierUtilizationPercentage> getTierUtilizationPercentages() {
        return tierUtilizationPercentages;
    }

    public void setTierUtilizationPercentages(List<TierUtilizationPercentage> tierUtilizationPercentages) {
        this.tierUtilizationPercentages = tierUtilizationPercentages;
    }

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

    public PoolServiceType getPoolServiceType() {
        return poolServiceType;
    }

    public void setPoolServiceType(PoolServiceType poolServiceType) {
        this.poolServiceType = poolServiceType;
    }

    public VolumeResourceType getVolumeResourceType() {
        return volumeResourceType;
    }

    public void setVolumeResourceType(VolumeResourceType volumeResourceType) {
        this.volumeResourceType = volumeResourceType;
    }

    public Integer getThinPoolMaximumSubscriptionPercentage() {
        return thinPoolMaximumSubscriptionPercentage;
    }

    public void setThinPoolMaximumSubscriptionPercentage(Integer thinPoolMaximumSubscriptionPercentage) {
        this.thinPoolMaximumSubscriptionPercentage = thinPoolMaximumSubscriptionPercentage;
    }

    public Integer getPoolMaximumUtilizationPercentage() {
        return poolMaximumUtilizationPercentage;
    }

    public void setPoolMaximumUtilizationPercentage(Integer poolMaximumUtilizationPercentage) {
        this.poolMaximumUtilizationPercentage = poolMaximumUtilizationPercentage;
    }

    public Boolean getThinVolumePreallocationSupported() {
        return thinVolumePreallocationSupported;
    }

    public void setThinVolumePreallocationSupported(Boolean thinVolumePreallocationSupported) {
        this.thinVolumePreallocationSupported = thinVolumePreallocationSupported;
    }

    public RegistrationStatus getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(RegistrationStatus registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public StorageSystem getStorageSystem() {
        return storageSystem;
    }

    public void setStorageSystem(StorageSystem storageSystem) {
        this.storageSystem = storageSystem;
    }
}
