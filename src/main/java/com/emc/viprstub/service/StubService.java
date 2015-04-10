package com.emc.viprstub.service;

import com.emc.viprstub.json.*;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 */
public interface StubService {


    String getToken();

    CreateBlockVolumeResponse createBlockVolume(CreateBlockVolumeRequest request);

    List<ViprProject> getViprProjects();

    void exportBlockVolumeToHost(ExportBlockVolumeRequest request);

    List<Host> getHosts();

    List<VirtualArray> getVirtualArrays();

    List<VirtualPool> getVirtualPools();

    VirtualPool getVirtualPoolInfo(String virtualPoolId);

    VirtualPool createVirtualPool(CreateBlockVirtualPoolsRequest request);

    VirtualPool assignStoragePools(String virtualPoolId, AssignStoragePoolsRequest request);

    StoragePool getStoragePoolInfo(String storagePoolId);

    List<StoragePool> getStoragePoolsReferences();

    ViprTask updateTask(String taskReference);

    List<BlockVolume> getBlockVolumes();

    List<ITL> getExportsByVolume(String volumeId);

    Initiator getInitiator(String initiatorId);

    ViprTask deleteBlockVolume(String blockVolumeId);

    String getInitiatorsByHostId(String id);
}
