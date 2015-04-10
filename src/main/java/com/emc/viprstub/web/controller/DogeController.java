package com.emc.viprstub.web.controller;

import com.emc.viprstub.json.*;
import com.emc.viprstub.service.StubService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = "/block/volumes/bulk.json", method = RequestMethod.GET)
    public @ResponseBody List<BlockVolume> getBlockVolumes() {
        return stubService.getBlockVolumes();
    }

    @RequestMapping(value = "/block/volumes/{id}/deactivate.json", method = RequestMethod.POST)
         public @ResponseBody ViprTask deleteBlockVolume(@PathVariable("id") String id) {
        return stubService.deleteBlockVolume(id);
    }

    @RequestMapping(value = "/compute/hosts/{id}/initiators.json", method = RequestMethod.GET)
    public @ResponseBody String getInitiatorsByHostId(@PathVariable("id") String id) {
        return stubService.getInitiatorsByHostId(id);
    }

    @RequestMapping(value = "/block/exports.json", method = RequestMethod.POST)
    public @ResponseBody void exportBlockVolume(@RequestBody ExportBlockVolumeRequest request) {
        stubService.exportBlockVolumeToHost(request);
    }

    @RequestMapping(value = "/compute/initiators/{id}.json", method = RequestMethod.GET)
    public @ResponseBody Initiator getInitiator(@PathVariable("id") String id) {
        return stubService.getInitiator(id);
    }

    @RequestMapping(value = "/block/volumes/exports/bulk.json", method = RequestMethod.POST)
    public @ResponseBody List<ITL> getExportsByVolume(@RequestBody String volumeId) {
        return stubService.getExportsByVolume(volumeId);
    }

    @RequestMapping(value = "/projects/bulk.json", method = RequestMethod.GET)
    public @ResponseBody List<ViprProject> getViprProjects() {
        return stubService.getViprProjects();
    }

    @RequestMapping(value = "/compute/hosts/bulk.json", method = RequestMethod.GET)
    public @ResponseBody List<Host> getHosts() {
        return stubService.getHosts();
    }

    @RequestMapping(value = "/vdc/varrays.json", method = RequestMethod.GET)
    public @ResponseBody List<VirtualArray> getVirtualArrays() {
        return stubService.getVirtualArrays();
    }

    @RequestMapping(value = "/block/vpools.json", method = RequestMethod.GET)
    public @ResponseBody List<VirtualPool> getVirtualPools() {
        return stubService.getVirtualPools();
    }

    @RequestMapping(value = "/block/vpools/{id}.json", method = RequestMethod.GET)
    public @ResponseBody VirtualPool getVirtualPool(@PathVariable("id") String id) {
        return stubService.getVirtualPoolInfo(id);
    }

    @RequestMapping(value = "/block/vpools.json", method = RequestMethod.POST)
    public @ResponseBody VirtualPool createVirtualPool(@RequestBody CreateBlockVirtualPoolsRequest request) {
        return stubService.createVirtualPool(request);
    }

    @RequestMapping(value = "/block/vpools/{virtualPoolId}/assign-matched-pools.json", method = RequestMethod.PUT)
    public @ResponseBody VirtualPool assignStoragePools(@PathVariable("virtualPoolId") String virtualPoolId, @RequestBody AssignStoragePoolsRequest request) {
        return stubService.assignStoragePools(virtualPoolId, request);
    }

    @RequestMapping(value = "/vdc/storage-pools/{id}.json", method = RequestMethod.GET)
    public @ResponseBody StoragePool getStoragePoolInfo(@PathVariable("id") String id) {
        return stubService.getStoragePoolInfo(id);
    }

    @RequestMapping(value = "/vdc/storage-pools.json", method = RequestMethod.GET)
    public @ResponseBody List<StoragePool> getStoragePools() {
        return stubService.getStoragePoolsReferences();
    }
}
