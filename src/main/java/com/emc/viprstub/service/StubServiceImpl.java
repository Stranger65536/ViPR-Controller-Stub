package com.emc.viprstub.service;

import com.emc.viprstub.json.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 */
@Service
public class StubServiceImpl implements StubService {


    @Autowired
    private TaskHandlerService taskHandlerService;

    private static final Map<String, BlockVolume> blockVolumes = new ConcurrentHashMap<>();
    private static final Map<String, VirtualPool> virtualPools = new ConcurrentHashMap<>();
    private static final Map<String, StoragePool> storagePools = new ConcurrentHashMap<>();
    private static final Map<String, ViprTask> tasks = new ConcurrentHashMap<>();
    private static final Map<BlockVolume, Initiator> initiators = new ConcurrentHashMap<>();
    private static final Map<String, Host> hosts = new ConcurrentHashMap<>();
    private static final ViprProject viprProject = new ViprProject();
    private static final VirtualArray virtualArray = new VirtualArray();


    public String getToken() {
        return "token-hyeken";
    }

    @PostConstruct
    private void init() {
        VirtualPool virtualPool = new VirtualPool();
        virtualPool.setId("123");
        virtualPools.put("123", virtualPool);

        viprProject.setId("vipr_project_sample_id");
        viprProject.setName("sample_project_name");
        virtualArray.setId("virtual_array_sample_id");
        virtualArray.setName("sample_varray_name");

    }


    @Override
    public CreateBlockVolumeResponse createBlockVolume(CreateBlockVolumeRequest request) {
        BlockVolume blockVolume = new BlockVolume();
        blockVolume.setId("BlockVolume_" + UUID.randomUUID());
        blockVolume.setName(request.getNameOfVolume());
        blockVolume.setAllocatedCapacityGb(request.getSizeOfVolume());
        blockVolume.setPreAllocatedSizeGb(request.getSizeOfVolume());
        blockVolume.setProvisionedCapacityGb(request.getSizeOfVolume());
        blockVolume.setRequestedCapacityGb(request.getSizeOfVolume());
        blockVolume.setVirtualPool(virtualPools.get(request.getVirtualPoolId()));
        blockVolumes.put(blockVolume.getId(), blockVolume);
        CreateBlockVolumeResponse response = new CreateBlockVolumeResponse();
        ViprTask task = new ViprTask();
        task.setName(blockVolume.getName());
        task.setStartTime(new Date().getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 4);
        task.setEndTime(calendar.getTimeInMillis());
        task.setOperationId("task_" + UUID.randomUUID());
        Link taskLink = new Link();
        taskLink.setReference(task.getOperationId());
        task.setTaskLink(taskLink);
        task.setTaskState("pending");
        tasks.put(task.getOperationId(), task);
        response.getViprTasks().add(task);
        taskHandlerService.processTask(task);
        return response;
    }

    @Override
    public List<ViprProject> getViprProjects() {
        return Collections.singletonList(viprProject);
    }

    @Override
    public void exportBlockVolumeToHost(ExportBlockVolumeRequest request) {
        ITL itl = new ITL();
        Initiator initiator = new Initiator();
        initiator.setId(request.getInitiatorIds().get(0));
        itl.setInitiator(initiator);
        initiators.put(request.getVolumes().get(0), initiator);
    }


    @Override
    public List<Host> getHosts() {
        return hosts.values().stream().collect(Collectors.toList());
    }

    @Override
    public List<VirtualArray> getVirtualArrays() {
        return Collections.singletonList(virtualArray);
    }

    @Override
    public List<VirtualPool> getVirtualPools() {
        return virtualPools.values().stream().collect(Collectors.toList());
    }

    @Override
    public VirtualPool getVirtualPoolInfo(String virtualPoolId) {
        return virtualPools.get(virtualPoolId);
    }

    @Override
    public VirtualPool createVirtualPool(CreateBlockVirtualPoolsRequest request) {
        VirtualPool virtualPool = new VirtualPool();
        virtualPool.setId("vp_" + UUID.randomUUID());
        virtualPool.setName(request.getName());
        virtualPools.put(virtualPool.getId(), virtualPool);
        return virtualPool;
    }

    @Override
    public VirtualPool assignStoragePools(String virtualPoolId, AssignStoragePoolsRequest request) {
        VirtualPool virtualPool = virtualPools.get(virtualPoolId);
        if (virtualPool != null) {
            virtualPool.setAssignedStoragePools(request.getAssignStoragePoolChanges().getAdd().getStoragePools());
        }
        return virtualPool;
    }

    @Override
    public StoragePool getStoragePoolInfo(String storagePoolId) {
        return storagePools.get(storagePoolId);
    }

    @Override
    public List<StoragePool> getStoragePoolsReferences() {
        return storagePools.values().stream().collect(Collectors.toList());
    }

    @Override
    public ViprTask updateTask(String taskReference) {
        if (taskReference == null || taskReference.isEmpty()) {
            return null;
        }
        return tasks.get(taskReference);
    }

    @Override
    public List<BlockVolume> getBlockVolumes() {
        return blockVolumes.values().stream().collect(Collectors.toList());
    }

    @Override
    public List<ITL> getExportsByVolume(String volumeId) {
        BlockVolume blockVolume = new BlockVolume();
        blockVolume.setId(volumeId);
        Initiator initiator = initiators.get(blockVolume);
        if (initiator != null) {
            ITL itl = new ITL();
            itl.setInitiator(initiator);
            return Collections.singletonList(itl);
        } else {
            return null;
        }
    }

    @Override
    public Initiator getInitiator(String initiatorId) {
        Optional<Initiator> initiator = initiators.values().stream().filter(i -> i.getId().equals(initiatorId)).findFirst();
        if (initiator.isPresent()) {
            return initiator.get();
        } else {
            return null;
        }

    }

    @Override
    public ViprTask deleteBlockVolume(String blockVolumeId) {
        if (blockVolumes.containsKey(blockVolumeId)) {
            blockVolumes.remove(blockVolumeId);
        }
        ViprTask task = new ViprTask();
        task.setTaskState("ready");
        task.setOperationId("task_" + UUID.randomUUID());
        task.setName("delete block volume");
        return task;
    }

    @Override
    public String getInitiatorsByHostId(String id) {
        return new JSONObject().put("initiator", initiators.values().toArray()).toString();
    }


}
