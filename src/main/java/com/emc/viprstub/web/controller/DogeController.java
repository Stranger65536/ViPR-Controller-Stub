package com.emc.viprstub.web.controller;

import com.emc.viprstub.json.CreateBlockVolumeRequest;
import com.emc.viprstub.json.CreateBlockVolumeResponse;
import com.emc.viprstub.json.ViprTask;
import com.emc.viprstub.service.StubService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 */
@RestController
@RequestMapping(value = "/", produces = "application/json;charset=UTF-8")
public class DogeController {

    @Autowired
    private StubService stubService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return new JSONObject().put("тест", "хуетест").toString();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String getToken() {
        return stubService.getToken();
    }

    @RequestMapping(value = "/block/volumes.json", method = RequestMethod.POST)
    public @ResponseBody CreateBlockVolumeResponse createBlockVolume(@RequestBody CreateBlockVolumeRequest request) {
        return stubService.createBlockVolume(request);
    }

    @RequestMapping(value = "/{taskId}.json", method = RequestMethod.GET)
    public @ResponseBody ViprTask updateTask(@PathVariable("taskId") String taskId) {
        return stubService.updateTask(taskId);
    }

}
