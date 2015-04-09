package com.emc.viprstub.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.List;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 *         Created on 23.09.2014.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateBlockVirtualPoolsRequest {

    @JsonProperty(value = "max_paths")
    private Integer maximumNumberOfPaths;

    @JsonProperty(value = "min_paths")
    private Integer minimumNumberOfPaths;

    @JsonProperty(value = "num_paths")
    private Integer numberOfPaths;

    @JsonProperty(value = "paths_per_initiator")
    private Integer pathsPerInitiator;

    @JsonProperty(value = "raid_levels")
    private List<String> raidLevels;

    @JsonProperty(value = "auto_tiering_policy_name")
    private String autoTieringPolicyName;

    @JsonProperty(value = "drive_type")
    private List<DriveType> driveTypes;

    @JsonProperty(value = "thin_volume_preallocation_percentage")
    private Integer thinVolumePreallocationPercentage;

    @JsonProperty(value = "multi_volume_consistency")
    private Boolean multiVolumeConsistency;

    @JsonProperty(value = "fast_expansion")
    private Boolean fastExpansion;

    @JsonProperty(value = "expandable")
    private Boolean expandable;

    @JsonProperty(value = "protection")
    private VirtualPoolProtection protection;

    @JsonProperty("high_availability")
    private VirtualPoolHighAvailability highAvailability;

    @JsonProperty(value = "unique_auto_tier_policy_names")
    private Boolean uniqueAutoTierPolicyNames;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "protocols")
    private List<Protocol> protocols;

    @JsonProperty(value = "varrays")
    private List<String> virtualArrays;

    @JsonProperty(value = "use_matched_pools")
    private Boolean useMatchedPools;

    @JsonProperty(value = "provisioning_type")
    private ProvisioningType provisioningType;

    @JsonProperty(value = "system_type")
    private SystemType systemType;


    public Integer getMaximumNumberOfPaths() {
        return maximumNumberOfPaths;
    }

    public void setMaximumNumberOfPaths(Integer maximumNumberOfPaths) {
        this.maximumNumberOfPaths = maximumNumberOfPaths;
    }

    public Integer getMinimumNumberOfPaths() {
        return minimumNumberOfPaths;
    }

    public void setMinimumNumberOfPaths(Integer minimumNumberOfPaths) {
        this.minimumNumberOfPaths = minimumNumberOfPaths;
    }

    public Integer getNumberOfPaths() {
        return numberOfPaths;
    }

    public void setNumberOfPaths(Integer numberOfPaths) {
        this.numberOfPaths = numberOfPaths;
    }

    public Integer getPathsPerInitiator() {
        return pathsPerInitiator;
    }

    public void setPathsPerInitiator(Integer pathsPerInitiator) {
        this.pathsPerInitiator = pathsPerInitiator;
    }

    public List<String> getRaidLevels() {
        return raidLevels;
    }

    public void setRaidLevels(List<String> raidLevels) {
        this.raidLevels = raidLevels;
    }

    public String getAutoTieringPolicyName() {
        return autoTieringPolicyName;
    }

    public void setAutoTieringPolicyName(String autoTieringPolicyName) {
        this.autoTieringPolicyName = autoTieringPolicyName;
    }

    public List<DriveType> getDriveTypes() {
        return driveTypes;
    }

    public void setDriveTypes(List<DriveType> driveTypes) {
        this.driveTypes = driveTypes;
    }

    public Integer getThinVolumePreallocationPercentage() {
        return thinVolumePreallocationPercentage;
    }

    public void setThinVolumePreallocationPercentage(Integer thinVolumePreallocationPercentage) {
        this.thinVolumePreallocationPercentage = thinVolumePreallocationPercentage;
    }

    public Boolean getMultiVolumeConsistency() {
        return multiVolumeConsistency;
    }

    public void setMultiVolumeConsistency(Boolean multiVolumeConsistency) {
        this.multiVolumeConsistency = multiVolumeConsistency;
    }

    public Boolean getFastExpansion() {
        return fastExpansion;
    }

    public void setFastExpansion(Boolean fastExpansion) {
        this.fastExpansion = fastExpansion;
    }

    public Boolean getExpandable() {
        return expandable;
    }

    public void setExpandable(Boolean expandable) {
        this.expandable = expandable;
    }

    public VirtualPoolProtection getProtection() {
        return protection;
    }

    public void setProtection(VirtualPoolProtection protection) {
        this.protection = protection;
    }

    public VirtualPoolHighAvailability getHighAvailability() {
        return highAvailability;
    }

    public void setHighAvailability(VirtualPoolHighAvailability highAvailability) {
        this.highAvailability = highAvailability;
    }

    public Boolean getUniqueAutoTierPolicyNames() {
        return uniqueAutoTierPolicyNames;
    }

    public void setUniqueAutoTierPolicyNames(Boolean uniqueAutoTierPolicyNames) {
        this.uniqueAutoTierPolicyNames = uniqueAutoTierPolicyNames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Protocol> getProtocols() {
        return protocols;
    }

    public void setProtocols(List<Protocol> protocols) {
        this.protocols = protocols;
    }

    public List<String> getVirtualArrays() {
        return virtualArrays;
    }

    public void setVirtualArrays(List<String> virtualArrays) {
        this.virtualArrays = virtualArrays;
    }

    public Boolean getUseMatchedPools() {
        return useMatchedPools;
    }

    public void setUseMatchedPools(Boolean useMatchedPools) {
        this.useMatchedPools = useMatchedPools;
    }

    public ProvisioningType getProvisioningType() {
        return provisioningType;
    }

    public void setProvisioningType(ProvisioningType provisioningType) {
        this.provisioningType = provisioningType;
    }

    public SystemType getSystemType() {
        return systemType;
    }

    public void setSystemType(SystemType systemType) {
        this.systemType = systemType;
    }
}
