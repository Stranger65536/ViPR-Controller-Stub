package com.emc.viprstub.test;

import com.emc.viprstub.config.ApplicationConfig;
import com.emc.viprstub.config.WebConfig;
import com.emc.viprstub.json.AssignStoragePoolChanges;
import com.emc.viprstub.json.AssignStoragePoolsRequest;
import com.emc.viprstub.json.AssignStoragePoolsWrapper;
import com.emc.viprstub.json.StoragePool;
import com.emc.viprstub.web.controller.DogeController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.Collections;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {
        ApplicationConfig.class})
@WebAppConfiguration
public class AppTest {

    @Autowired
    private DogeController controller;

    @Test
    @Ignore
    public void foo() throws IOException {
        AssignStoragePoolsRequest assignStoragePoolsRequest = new AssignStoragePoolsRequest();
        assignStoragePoolsRequest.setAssignStoragePoolChanges(new AssignStoragePoolChanges());
        assignStoragePoolsRequest.getAssignStoragePoolChanges().setAdd(new AssignStoragePoolsWrapper());
        assignStoragePoolsRequest.getAssignStoragePoolChanges().getAdd().setStoragePoolsIds(Collections.singletonList("123"));

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(assignStoragePoolsRequest));
        String test = "{\n" +
                "    \"assigned_pool_changes\": {\n" +
                "        \"add\": {\n" +
                "            \"storage_pool\": [\n" +
                "                \"urn:storageos:StoragePool:1h2jf5c78-1c7v-9s6e-a4a1-4add2d04ee32:vdc1\"\n" +
                "            ]\n" +
                "        }\n" +
                "    }\n" +
                "}";
        AssignStoragePoolsRequest test2 = mapper.readValue(test, AssignStoragePoolsRequest.class);

    }

    @Test
    @Ignore
    public void getViprProjectsTest() {
        String json = controller.getViprProjects();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(json);
        String prettyJsonString = gson.toJson(je);
        System.out.println(prettyJsonString);

    }
}
