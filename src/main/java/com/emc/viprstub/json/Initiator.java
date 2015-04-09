package com.emc.viprstub.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 *         Created on 01.10.2014.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Initiator {

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private String id;

    @JsonProperty("link")
    private Link link;

    @JsonProperty("inactive")
    private Boolean inactive;

    @JsonProperty("global")
    private Boolean global;

    @JsonProperty("remote")
    private Boolean remote;

    @JsonProperty("tags")
    private List<String> tags;

    @JsonProperty("internal")
    private Boolean internal;

    @JsonProperty("host")
    private Host host;

    @JsonProperty("protocol")
    private String protocol;

    @JsonProperty("creation_time")
    private Long creationTime;

    @JsonProperty("registration_status")
    private String registrationStatus;

    @JsonProperty("hostname")
    private String hostname;

    @JsonProperty("initiator_node")
    private String initiatorNode;

    @JsonProperty("initiator_port")
    private String initiatorPort;

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

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }

    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getInitiatorNode() {
        return initiatorNode;
    }

    public void setInitiatorNode(String initiatorNode) {
        this.initiatorNode = initiatorNode;
    }

    public String getInitiatorPort() {
        return initiatorPort;
    }

    public void setInitiatorPort(String initiatorPort) {
        this.initiatorPort = initiatorPort;
    }
}
