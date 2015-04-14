package com.emc.viprstub.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AssignStoragePoolChanges {
    @JsonProperty("add")
    private AssignStoragePoolsWrapper add;
    @JsonProperty("remove")
    private AssignStoragePoolsWrapper remove;

    public AssignStoragePoolsWrapper getAdd() {
        return add;
    }

    public void setAdd(AssignStoragePoolsWrapper add) {
        this.add = add;
    }

    public AssignStoragePoolsWrapper getRemove() {
        return remove;
    }

    public void setRemove(AssignStoragePoolsWrapper remove) {
        this.remove = remove;
    }
}
