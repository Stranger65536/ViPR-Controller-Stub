package com.emc.viprstub.service.stuff;

import com.emc.viprstub.json.ViprTask;
import com.emc.viprstub.service.DataHolder;

import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 */
public class TaskHandler implements Runnable {


    @Override
    public void run() {
        Thread.currentThread().setName("doge thread");
        while (!Thread.currentThread().isInterrupted()) {
            if (!DataHolder.getPendingTasks().isEmpty()) {
                Iterator<Map.Entry<String, ViprTask>> iterator = DataHolder.getPendingTasks().entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, ViprTask> entry = iterator.next();
                    if (entry.getValue().getEndTime() <= Calendar.getInstance().getTimeInMillis()) {
                        entry.getValue().setTaskState("ready");
                        iterator.remove();
                        DataHolder.getCompleteTasks().put(entry.getKey(), entry.getValue());
                    }

                }
                try {
                    Thread.currentThread().sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
//                    DataHolder.getMonitor().wait();
                    synchronized (DataHolder.getMonitor()) {
                        DataHolder.getMonitor().wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
