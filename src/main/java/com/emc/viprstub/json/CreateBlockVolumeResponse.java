package com.emc.viprstub.json;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vladislav.trofimov@emc.com
 * @date 18.09.2014
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateBlockVolumeResponse {

    @JsonProperty("task")
    private List<ViprTask> viprTasks = new ArrayList<>();

    public List<ViprTask> getViprTasks() {
        return viprTasks;
    }

}