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

    private final String doge = "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNmNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNhsooydNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNms++oshyhNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNmo++++ossyhNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNNNNNdydNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNdo+o+oossyyydNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNNNNNs/+smNNNNNNNNNNNNNNNNNNNNNNNNNNNNNdooo+osyyyyyyymNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNNNNNy:/+oymNNNNNNNNNNNNNNNNNNNNNNNNNNd++ysosyyhhhhyyhmNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNNNNNm::+ooshmNNNNNNNNNNNNNNNNNNNNNNNdooshssshddmmdhyydNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNNNNNN/:/oososhmNNNNNNNNNNNNNNmmdddhhooshyssyhdmNmmyssyNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNNNNNNs:/+ossooosydmddhhyssoo++++/+ossshhyooosdmNmmyooomNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNNNNNNd:/++++///////+//////://::////+oyhhysssymNNmds+oomNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNNNNNNN++o//::::////+//////////////////+osyssdNNmdysoshdmNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNNNNNNNs/::::::///+++++///////////////////+oshhmdyssoysosydNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNNNNNNNd:::::::://+o++//++///////////////////++oooooooyyys+smNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNNNNNNms------::/++o+//////////+/++////////::::::/++ooyhhyo/+dNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNNNNNdo::------:/+oo++//:::::://+o+++//////:::::::://+oyhhy+/+mNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNNNms::::------://o+++/:-:::-::///+++++/////::/:::::///+oyso+/sNNNNNNNmhdNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNNh:--:::-:---::/+o+++:---::::://////+/////::///:::://///+o++/+NNNNNNm.``sNNNNNms+smNNNNNNmdmNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNy---:::::/::-://+++//::::::+yhhhysoo+///:::::://::::://///+//sNNNNNNh   /NNNNm/   oNNNNNd-.-hNNNNNNmdhyyhhmNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNh----:sdmmhs:://:///::/:://sdsydNNNmho+//::::::::::::::://////sNNNNNNh   /NNNN+    -NNNNm-  `hNNNNdo-.`  ``.:yNNNNNms+omNNNNNNmdmNNNNNNNNNNNNN\n" +
            "NNNNNNNm/----/yhdNhm+/:::::::://+sdNs/mNMNNmh/:::--------------:::::///hNNNNNd   /NNNs     .NNNNo   sNNNm+`  `.::-`  `oNNNNh   oNNNNNh-.-dNNNNNmysymNN\n" +
            "NNNNNNNy.....//mmNNd+::::::-::/oshNNhsmNNNmy/::---.----:----------:::///mNNNNm   /NNy` `/   mNNh   /mNNm:   :hmmNmd/   hNNNy   /NNNNd`   +NNNNNo   hNN\n" +
            "NNNNNNNo.`...:oymmy+::::----:::+ooyddhyhys/:::::---..------------::::///yNNNNN`  :Nh` `sd   mNm.  -mNNN+   +mNNNNNNm   +NNNh   +NNNd.    -NNNNh`  -mNN\n" +
            "NNNNNNN/.`..--:+++::::------:::/++////////:/:::---........-------::://+/+NNNNN.  :h` `yNm   hm/  `dNNNm`  .mNNNNNNNN`  /NNNd   +NNm:  `  .NNNm-  `dNNN\n" +
            "NNNNNNN+....-:::-------------:::///:::/:://:::-------..-------::::::/+++/dNNNN/  .. `yNNN`  yo  `yNNNNd   /NNNNNNNNd   oNNNm   /Nm/  -s   NNN+   yNNNN\n" +
            "NNNNNNNs.....--------:::------::::::------------------------::::::://++++hNNNNs    `yNNNN-  :`  sNNNNNm`  .mNNNNNNm:  .mNNNN`  :m/  :md   mNy   oNNNNN\n" +
            "NNNNNNNm.......-/++ooo+/:--------:::-------..-..---------:::::::::://++++sNNNNm.  .yNNNNNo     oNNNNNNNs   -ydddds-  `hNNNNN.  :+  :mNm   hd.  /mNNNNN\n" +
            "NNNNNNNN-....:ohddddmNmmh/-...-----:------...--...------::::::::::://+++++mNNNNdosdNNNNNNm:``.yNNNNNNNNNs.   `..`  `:dNNNNNN/  `  :mNNN`  s-  -mNNNNNN\n" +
            "NNNNNNNN/...:mNNNNNMMMNNNy:-..----:::---------------:::::::::--::::::/++++hNNNNNNNNNNNNNNNmhhmNNNNNNNNNNNms/.....-+hmNNNNNNNh    :mNNNN:  .  .dNNNNNNN\n" +
            "NNNNNNNN+..`:mNNNNNMMMMMNh/:-::-:-:::------------::::::::::-------::/+++++sNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNmmddmNNNNNNNNNNNN/`.+mNNNNNy    -dNNNNNNNN\n" +
            "NNNNNNNNo....omNNNNNNNNNmy+//:-::::::::---::::::::::-:::::------::://+++++oNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNmddNNNNNNNNs::omNNNNNNNNN\n" +
            "NNNNNNNNs...-/dNNNNNNNNmdyo//+/////://///:::::::::::::::----::::::///+++++oNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNh-..-/sdmNNNNNNmdhyo+////////+os+//::::::::::::::::::::::///+++++/yNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNm-..-/shdmmmmmmmmmhyo+////oyhdmho/:::::::::::::::-::::::////++++++mNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNN+..--/shmNNNNNNNNNmdhhshdmmmhyo/::::::::::::::----:::://///+++++hNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNh..----sdmmNNNNNNNNNNNNdhhyso+/:::::::://::::-----::::://///+++yNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNN/....--+sydmmmddhyyyhyooo+//::::/:////::::::--::::::::/:////+hNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNNd-...----:+syyssso+/++////::://///////:::-----::::::://////odNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNNNy-....---::/++/:/::::/:////////////:::-----::::/::::::::+ymNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNNNNy-..----------:::::::/::://////////::::::::::/::::::/+ymNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNNNNNh:---------:-:::::::///:://////////::://::///::::/ohmNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNNNNNNmo--------:-::::::::://///////////////+///:///shmNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNNNNNNNNdo:------:::::::::::///////////////++/++shdNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNNNNNNNNNNmyo/::-::::::::::://///////////+oyhdmNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNNNNNNNNNNNNNNmhyso++/:::://////++osyyhdmNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNmmmmmmmmNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN";


    @RequestMapping(value="/", method = RequestMethod.GET)
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>(doge, HttpStatus.OK);
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ResponseEntity<String> login() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-SDS-AUTH-TOKEN", "token");
        return new ResponseEntity<>(new JSONObject().put("login", "ok").toString(), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/doge", method = RequestMethod.GET)
    public String test() {
        return doge;
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
