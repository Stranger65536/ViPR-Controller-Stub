package com.emc.viprstub.web.controller;

import com.emc.viprstub.json.*;
import com.emc.viprstub.service.StubService;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 */
@RestController
@RequestMapping(value = "/", produces = "application/json;charset=UTF-8")
public class DogeController {

    @Autowired
    private StubService stubService;


    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ResponseEntity<String> login() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-SDS-AUTH-TOKEN", "token");
        return new ResponseEntity<>(new JSONObject().put("login", "ok").toString(), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return new JSONObject().put("тест", "хуетест").toString();
    }

    @RequestMapping(value = "/block/volumes.json", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public @ResponseBody CreateBlockVolumeResponse createBlockVolume(@RequestBody CreateBlockVolumeRequest request) {
        return stubService.createBlockVolume(request);
    }

    @RequestMapping(value = "/tasks/{taskId}.json", method = RequestMethod.GET)
    public @ResponseBody ViprTask updateTask(@PathVariable("taskId") String taskId) {
        return stubService.updateTask(taskId);
    }

    @RequestMapping(value = "/block/volumes/bulk.json", method = RequestMethod.GET)
    public @ResponseBody String getBlockVolumes() {
        return new JSONObject().put("id", stubService.getBlockVolumes().stream().map(BlockVolume::getId).collect(Collectors.toList())).toString();
    }

    @RequestMapping(value = "/block/volumes/{id}.json", method = RequestMethod.GET)
    public @ResponseBody BlockVolume getBlockVolumeById(@PathVariable("id") String id) {
        return stubService.getBlockVolumeById(id);
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

    @RequestMapping(value = "/block/volumes/exports/bulk.json", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String getExportsByVolume(@RequestBody GetExportsByVolumeRequest request) {
        return new JSONObject().put("itl", stubService.getExportsByVolume(request.getVolumeIds().get(0))).toString();
    }

    @RequestMapping(value = "/projects/bulk.json", method = RequestMethod.GET)
    public @ResponseBody String getViprProjects() {

        return new JSONObject().put("id", stubService.getViprProjects().stream().map(ViprProject::getId).collect(Collectors.toList())).toString();
    }

    @RequestMapping(value = "/projects/{id}.json", method = RequestMethod.GET)
    public @ResponseBody ViprProject getViprProject(@PathVariable("id") String id) {
        return stubService.getViprProjectById(id);
    }



    @RequestMapping(value = "/compute/hosts/bulk.json", method = RequestMethod.GET)
    public @ResponseBody String getHosts() {
        return new JSONObject().put("id", stubService.getHosts().stream().map(Host::getId).collect(Collectors.toList())).toString();
    }

    @RequestMapping(value = "/compute/hosts/{id}.json", method = RequestMethod.GET)
    public @ResponseBody Host getHostById(@PathVariable("id") String id) {
        return stubService.getHostById(id);
    }

    @RequestMapping(value = "/vdc/varrays.json", method = RequestMethod.GET)
    public @ResponseBody String getVirtualArrays() {
        return new JSONObject().put("varray", stubService.getVirtualArrays()).toString();
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
    public @ResponseBody String getStoragePools() {

        ObjectMapper mapper = new ObjectMapper();
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        ObjectNode response = jsonNodeFactory.objectNode();
        ArrayNode storagePools = jsonNodeFactory.arrayNode();
        stubService.getStoragePoolsReferences().stream().forEach(reference -> storagePools.add(mapper.convertValue(reference, JsonNode.class)));
        response.put("storage_pool", storagePools);


        return response.toString();
    }
}
