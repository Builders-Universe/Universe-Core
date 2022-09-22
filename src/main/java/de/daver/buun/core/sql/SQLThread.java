package de.daver.buun.core.sql;

import de.daver.buun.core.thread.worker.QueuedWorkerThread;

public class SQLThread extends QueuedWorkerThread {

    public void clear(){
        getQueue().forEach(Runnable::run);
        getQueue().clear();
    }
}
