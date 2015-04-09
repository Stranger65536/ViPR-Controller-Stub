package com.emc.viprstub.service;

import com.emc.viprstub.json.*;
import com.emc.viprstub.service.stuff.TaskHandler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 */
@Service
public class StubServiceImpl implements StubService {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    public String getToken() {
        return "token-hyeken";
    }

    @PostConstruct
    public void init() {
        TaskHandler taskHandler = new TaskHandler();
        executorService.submit(taskHandler);
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
        blockVolume.setVirtualPool(DataHolder.getVirtualPools().get(request.getVirtualPoolId()));
        DataHolder.getBlockVolumes().put(blockVolume.getId(), blockVolume);
        CreateBlockVolumeResponse response = new CreateBlockVolumeResponse();
        ViprTask task = new ViprTask();
        task.setName(blockVolume.getName());
        task.setStartTime(new Date().getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 60);
        task.setEndTime(calendar.getTimeInMillis());
        task.setOperationId("task_" + UUID.randomUUID());
        Link taskLink = new Link();
        taskLink.setReference(task.getOperationId());
        task.setTaskLink(taskLink);
        task.setTaskState("pending");
        DataHolder.getPendingTasks().put(task.getOperationId(), task);
        response.getViprTasks().add(task);
        synchronized (DataHolder.getMonitor()) {
            DataHolder.getMonitor().notify();
        }
        return response;
    }

    @Override
    public List<ViprProject> getViprProjects() {
        return null;
    }

    @Override
    public void exportBlockVolumeToHost(String blockVolumeId, String hostId) {

    }

    @Override
    public List<Host> getHosts() {
        return null;
    }

    @Override
    public List<VirtualArray> getVirtualArrays() {
        return null;
    }

    @Override
    public List<VirtualPool> getVirtualPools() {
        return null;
    }

    @Override
    public VirtualPool getVirtualPoolInfo(String virtualPoolId) {
        return null;
    }

    @Override
    public VirtualPool createVirtualPool(CreateBlockVirtualPoolsRequest request) {
        return null;
    }

    @Override
    public VirtualPool assignStoragePools(String virtualPoolId, List<String> storagePoolIds) {
        return null;
    }

    @Override
    public StoragePool getStoragePoolInfo(String storagePoolId) {
        return null;
    }

    @Override
    public List<StoragePool> getStoragePoolsReferences() {
        return null;
    }

    @Override
    public ViprTask updateTask(String taskReference) {
        if (DataHolder.getCompleteTasks().containsKey(taskReference)) {
            return DataHolder.getCompleteTasks().get(taskReference);
        }
        if (DataHolder.getPendingTasks().containsKey(taskReference)) {
            return DataHolder.getPendingTasks().get(taskReference);
        }
        return null;
    }

    @Override
    public List<BlockVolume> getBlockVolumes() {
        return null;
    }

    @Override
    public List<ITL> getExportsByVolume(String volumeId) {
        return null;
    }

    @Override
    public Initiator getInitiator(String initiatorId) {
        return null;
    }

    @Override
    public ViprTask deleteBlockVolume(String blockVolumeId) {
        return null;
    }

}
