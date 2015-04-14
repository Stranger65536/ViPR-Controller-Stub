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
        virtualArray.setId("urn:storageos:VirtualArray:ca41c8a9-e47e-4b91-bef9-8212fd8bcb32:vdc1");
        virtualArray.setName("Virtual Array 0");

        StorageSystem vnx = new StorageSystem();
        vnx.setId("urn:storageos:StorageSystem:bbdc3765-e1c6-49f1-a42f-11129cbb2aa1:vdc1");
        StorageSystem vmax = new StorageSystem();
        vmax.setId("urn:storageos:StorageSystem:nnop3765-e1q6-59w1-z42s-12729cbps2aa1:vdc1");


        StoragePool storagePool;
        storagePool = new StoragePool();
        storagePool.setName("Pool 1");
        storagePool.setStorageSystem(vnx);
        storagePool.setName("CLARIION+FNM00104900081+POOL+U+Pool 1");
        storagePool.setId("urn:storageos:StoragePool:3a7e5c78-7c7b-4b6e-a4a1-4add2d04ee32:vdc1");
        storagePool.setInactive(false);
        storagePool.setProtocols(Arrays.asList(Protocol.values()));
        storagePool.setPercent_subscribed(1L);
        storagePool.setCreationTime(1410533235918L);
        storagePool.setNativeGuid("CLARIION+FNM00104900081+POOL+U+Pool 1");
        storagePool.setOperationalStatus(OperationalStatus.READY);
        storagePool.setRegistrationStatus(RegistrationStatus.REGISTERED);
        storagePool.setUsableGb(536L);
        storagePool.setFreeGb(533L);
        storagePool.setUsedGb(3L);
        storagePool.setPercentUsed(1L);
        storagePool.setSubscribedGb(4L);
        storagePool.setConnectedVirtualArrays(Collections.singletonList("urn:storageos:VirtualArray:ca41c8a9-e47e-4b91-bef9-8212fd8bcb32:vdc1"));
        storagePool.setTaggedVirtualArrays(Collections.singletonList("urn:storageos:VirtualArray:ca41c8a9-e47e-4b91-bef9-8212fd8bcb32:vdc1"));
        storagePool.setMaximumStorageResources(-1);
        storagePool.setNumberOfStorageResources(0);
        storagePool.setThinVolumeMaximumSizeGb(262144L);
        storagePool.setThinVolumeMinimumSizeGb(0L);
        storagePool.setThickVolumeMaximumSizeGb(519L);
        storagePool.setThickVolumeMinimumSizeGb(0L);
        storagePool.setRaidLevels(Collections.singletonList("RAID5"));
        storagePool.setDriveTypes(Collections.singletonList("SAS"));
        storagePool.setCopyTypes(Arrays.asList("UNSYNC_UNASSOC", "UNSYNC_ASSOC"));
        storagePool.setPoolName("Pool 1");
        storagePool.setPoolServiceType(PoolServiceType.BLOCK);
        storagePool.setVolumeResourceType(VolumeResourceType.THIN_AND_THICK);
        storagePool.setThinPoolMaximumSubscriptionPercentage(300);
        storagePool.setPoolMaximumUtilizationPercentage(75);
        storagePool.setThinVolumePreallocationSupported(false);
        storagePools.put(storagePool.getId(), storagePool);

        storagePool = new StoragePool();
        storagePool.setName("Pool 0");
        storagePool.setStorageSystem(vnx);
        storagePool.setName("CLARIION+FNM00104900081+POOL+U+Pool 1");
        storagePool.setId("urn:storageos:StoragePool:3a7jgc78-7c7b-4b6e-a4a1-4add2d08gl32:vdc1");
        storagePool.setInactive(false);
        storagePool.setProtocols(Arrays.asList(Protocol.values()));
        storagePool.setPercent_subscribed(1L);
        storagePool.setCreationTime(1410533235918L);
        storagePool.setNativeGuid("CLARIION+FNM00104900081+POOL+U+Pool 1");
        storagePool.setOperationalStatus(OperationalStatus.READY);
        storagePool.setRegistrationStatus(RegistrationStatus.REGISTERED);
        storagePool.setUsableGb(264L);
        storagePool.setFreeGb(263L);
        storagePool.setUsedGb(1L);
        storagePool.setPercentUsed(1L);
        storagePool.setSubscribedGb(17L);
        storagePool.setConnectedVirtualArrays(Collections.singletonList("urn:storageos:VirtualArray:ca41c8a9-e47e-4b91-bef9-8212fd8bcb32:vdc1"));
        storagePool.setTaggedVirtualArrays(Collections.singletonList("urn:storageos:VirtualArray:ca41c8a9-e47e-4b91-bef9-8212fd8bcb32:vdc1"));
        storagePool.setMaximumStorageResources(-1);
        storagePool.setNumberOfStorageResources(0);
        storagePool.setThinVolumeMaximumSizeGb(262144L);
        storagePool.setThinVolumeMinimumSizeGb(0L);
        storagePool.setThickVolumeMaximumSizeGb(519L);
        storagePool.setThickVolumeMinimumSizeGb(0L);
        storagePool.setRaidLevels(Collections.singletonList("RAID10"));
        storagePool.setDriveTypes(Collections.singletonList("SAS"));
        storagePool.setCopyTypes(Arrays.asList("UNSYNC_UNASSOC", "UNSYNC_ASSOC"));
        storagePool.setPoolName("Pool 0");
        storagePool.setPoolServiceType(PoolServiceType.BLOCK);
        storagePool.setVolumeResourceType(VolumeResourceType.THIN_AND_THICK);
        storagePool.setThinPoolMaximumSubscriptionPercentage(300);
        storagePool.setPoolMaximumUtilizationPercentage(75);
        storagePool.setThinVolumePreallocationSupported(false);
        storagePools.put(storagePool.getId(), storagePool);

        storagePool = new StoragePool();
        storagePool.setName("Pool 2");
        storagePool.setStorageSystem(vmax);
        storagePool.setName("VMAX2+000195700999+POOL+U+Pool 2");
        storagePool.setId("urn:storageos:StoragePool:1h2jf5c78-1c7v-9s6e-a4a1-4add2d04ee32:vdc1");
        storagePool.setInactive(false);
        storagePool.setProtocols(Arrays.asList(Protocol.values()));
        storagePool.setPercent_subscribed(1L);
        storagePool.setCreationTime(1410533235918L);
        storagePool.setNativeGuid("VMAX2+000195700999+POOL+U+Pool 2");
        storagePool.setOperationalStatus(OperationalStatus.READY);
        storagePool.setRegistrationStatus(RegistrationStatus.REGISTERED);
        storagePool.setUsableGb(264L);
        storagePool.setFreeGb(263L);
        storagePool.setUsedGb(1L);
        storagePool.setPercentUsed(1L);
        storagePool.setSubscribedGb(17L);
        storagePool.setConnectedVirtualArrays(Collections.singletonList("urn:storageos:VirtualArray:ca41c8a9-e47e-4b91-bef9-8212fd8bcb32:vdc1"));
        storagePool.setTaggedVirtualArrays(Collections.singletonList("urn:storageos:VirtualArray:ca41c8a9-e47e-4b91-bef9-8212fd8bcb32:vdc1"));
        storagePool.setMaximumStorageResources(-1);
        storagePool.setNumberOfStorageResources(0);
        storagePool.setThinVolumeMaximumSizeGb(262144L);
        storagePool.setThinVolumeMinimumSizeGb(0L);
        storagePool.setThickVolumeMaximumSizeGb(519L);
        storagePool.setThickVolumeMinimumSizeGb(0L);
        storagePool.setRaidLevels(Collections.singletonList("RAID10"));
        storagePool.setDriveTypes(Collections.singletonList("SAS"));
        storagePool.setCopyTypes(Arrays.asList("UNSYNC_UNASSOC", "UNSYNC_ASSOC"));
        storagePool.setPoolName("Pool 2");
        storagePool.setPoolServiceType(PoolServiceType.BLOCK);
        storagePool.setVolumeResourceType(VolumeResourceType.THIN_AND_THICK);
        storagePool.setThinPoolMaximumSubscriptionPercentage(300);
        storagePool.setPoolMaximumUtilizationPercentage(75);
        storagePool.setThinVolumePreallocationSupported(false);
        storagePools.put(storagePool.getId(), storagePool);


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
            List<StoragePool> assignedStoragePools = new LinkedList<>();
            for (String poolId : request.getAssignStoragePoolChanges().getAdd().getStoragePoolsIds()) {
                StoragePool storagePool = storagePools.get(poolId);
                if (storagePool != null) {
                    assignedStoragePools.add(storagePool);
                }
            }

            virtualPool.setAssignedStoragePools(assignedStoragePools);
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
