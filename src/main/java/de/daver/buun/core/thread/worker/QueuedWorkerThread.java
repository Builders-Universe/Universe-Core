package de.daver.buun.core.thread.worker;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueuedWorkerThread extends LoopedWorkerThread{

    private final Queue<Runnable> queue;

    public QueuedWorkerThread(){
        queue = new ConcurrentLinkedQueue<>();
    }

    public void enque(Runnable method){
        queue.add(method);
    }

    @Override
    protected void onLoop() {
        if(queue.isEmpty()) return;
        queue.remove().run();
    }

    protected Queue<Runnable> getQueue(){
        return this.queue;
    }
}
