package com.emc.viprstub.service;

import com.emc.viprstub.json.ViprTask;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 */
@Service
public class TaskHandlerServiceImpl implements TaskHandlerService {

    @Override
    @Async
    public void processTask(ViprTask task) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            task.setTaskState("error");
        }
        task.setTaskState("ready");
    }
}
