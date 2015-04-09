package com.emc.viprstub.service;

import com.emc.viprstub.json.BlockVolume;
import com.emc.viprstub.json.ViprTask;
import com.emc.viprstub.json.VirtualPool;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 */
@Component
@Scope(value = "singleton")
public class DataHolder {

    @PostConstruct
    private void init() {
        VirtualPool virtualPool = new VirtualPool();
        virtualPool.setId("123");
        virtualPools.put("123", virtualPool);

    }

    private static final Map<String, BlockVolume> blockVolumes = new ConcurrentHashMap<>();
    private static final Map<String, VirtualPool> virtualPools = new ConcurrentHashMap<>();
    private static final Map<String, ViprTask> completeTasks = new ConcurrentHashMap<>();
    private static final Map<String, ViprTask> pendingTasks = new ConcurrentHashMap<>();
    private static final Object monitor = new Object();


    public static Map<String, BlockVolume> getBlockVolumes() {
        return blockVolumes;
    }

    public static Map<String, VirtualPool> getVirtualPools() {
        return virtualPools;
    }


    public static Map<String, ViprTask> getCompleteTasks() {
        return completeTasks;
    }

    public static Map<String, ViprTask> getPendingTasks() {
        return pendingTasks;
    }

    public static Object getMonitor() {
        return monitor;
    }
}
