package com.emc.viprstub.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssignStoragePoolsRequest {
    @JsonProperty("assigned_pool_changes")
    private AssignStoragePoolChanges assignStoragePoolChanges;

    public AssignStoragePoolChanges getAssignStoragePoolChanges() {
        return assignStoragePoolChanges;
    }

    public void setAssignStoragePoolChanges(AssignStoragePoolChanges assignStoragePoolChanges) {
        this.assignStoragePoolChanges = assignStoragePoolChanges;
    }
}
