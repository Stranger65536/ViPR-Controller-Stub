package com.emc.viprstub.service;

import com.emc.viprstub.json.ViprTask;
import org.springframework.scheduling.annotation.Async;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 */
public interface TaskHandlerService {
    @Async
    void processTask(ViprTask task);
}
