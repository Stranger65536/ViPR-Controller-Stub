package com.emc.viprstub.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 *         Created on 23.09.2014.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VirtualPool {


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

    @JsonProperty(value = "type")
    private VirtualPoolType virtualPoolType;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "protocols")
    private List<Protocol> protocols = new ArrayList<>();

    @JsonProperty(value = "expandable")
    private Boolean expandable;

    @JsonProperty(value = "creation_time")
    private Long creationTime;

    @JsonProperty(value = "provisioning_type")
    private ProvisioningType provisioningType;

    @JsonProperty(value = "num_paths")
    private Integer numberOfPaths;

    @JsonProperty(value = "num_resources")
    private Integer numberOfResources;

    @JsonProperty(value = "use_matched_pools")
    private Boolean useMatchedPools;

    @JsonProperty(value = "assigned_storage_pools")
    private List<StoragePool> assignedStoragePools = new ArrayList<>();

    @JsonProperty(value = "matched_storage_pools")
    private List<StoragePool> matchedStoragePools = new ArrayList<>();


    @JsonProperty(value = "invalid_matched_pools")
    private List<StoragePool> invalidMatchedPools = new ArrayList<>();

    @JsonProperty(value = "raid_levels")
    private List<String> raidLevels = new ArrayList<>();

    @JsonProperty(value = "unique_auto_tier_policy_names")
    private Boolean uniqueAutoTierPolicyNames;

    @JsonProperty(value = "varrays")
    private List<VirtualArray> virtualArrays = new ArrayList<>();

    @JsonProperty(value = "protection")
    private VirtualPoolProtection protection;

    @JsonProperty("high_availability")
    private VirtualPoolHighAvailability highAvailability = new VirtualPoolHighAvailability();


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

    public VirtualPoolType getVirtualPoolType() {
        return virtualPoolType;
    }

    public void setVirtualPoolType(VirtualPoolType virtualPoolType) {
        this.virtualPoolType = virtualPoolType;
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

    public Boolean getExpandable() {
        return expandable;
    }

    public void setExpandable(Boolean expandable) {
        this.expandable = expandable;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }

    public ProvisioningType getProvisioningType() {
        return provisioningType;
    }

    public void setProvisioningType(ProvisioningType provisioningType) {
        this.provisioningType = provisioningType;
    }

    public Integer getNumberOfPaths() {
        return numberOfPaths;
    }

    public void setNumberOfPaths(Integer numberOfPaths) {
        this.numberOfPaths = numberOfPaths;
    }

    public Integer getNumberOfResources() {
        return numberOfResources;
    }

    public void setNumberOfResources(Integer numberOfResources) {
        this.numberOfResources = numberOfResources;
    }

    public Boolean getUseMatchedPools() {
        return useMatchedPools;
    }

    public void setUseMatchedPools(Boolean useMatchedPools) {
        this.useMatchedPools = useMatchedPools;
    }

    public List<StoragePool> getAssignedStoragePools() {
        return assignedStoragePools;
    }

    public void setAssignedStoragePools(List<StoragePool> assignedStoragePools) {
        this.assignedStoragePools = assignedStoragePools;
    }

    public List<StoragePool> getMatchedStoragePools() {
        return matchedStoragePools;
    }

    public void setMatchedStoragePools(List<StoragePool> matchedStoragePools) {
        this.matchedStoragePools = matchedStoragePools;
    }

    public List<StoragePool> getInvalidMatchedPools() {
        return invalidMatchedPools;
    }

    public void setInvalidMatchedPools(List<StoragePool> invalidMatchedPools) {
        this.invalidMatchedPools = invalidMatchedPools;
    }

    public List<String> getRaidLevels() {
        return raidLevels;
    }

    public void setRaidLevels(List<String> raidLevels) {
        this.raidLevels = raidLevels;
    }

    public Boolean getUniqueAutoTierPolicyNames() {
        return uniqueAutoTierPolicyNames;
    }

    public void setUniqueAutoTierPolicyNames(Boolean uniqueAutoTierPolicyNames) {
        this.uniqueAutoTierPolicyNames = uniqueAutoTierPolicyNames;
    }

    public List<VirtualArray> getVirtualArrays() {
        return virtualArrays;
    }

    public void setVirtualArrays(List<VirtualArray> virtualArrays) {
        this.virtualArrays = virtualArrays;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VirtualPool pool = (VirtualPool) o;

        return id.equals(pool.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
