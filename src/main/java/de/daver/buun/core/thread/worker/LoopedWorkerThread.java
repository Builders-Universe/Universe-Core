package de.daver.buun.core.thread.worker;

import de.daver.buun.core.exception.ExceptionHandler;

public abstract class LoopedWorkerThread extends ThreadWorker{

    private boolean running;
    private long timeoutMillis;

    public void setTimeout(long millis){
        this.timeoutMillis = millis;
    }

    public long getTimeout(){
        return this.timeoutMillis;
    }

    @Override
    public void run() {
        while (running) {
            onLoop();
            new ExceptionHandler<>().run(() -> Thread.sleep(timeoutMillis));
        }
    }

    @Override
    protected void onStart() {
        running = true;
    }

    @Override
    protected void onStop() {
        running = false;
    }

    public boolean isRunning(){
        return this.running;
    }

    protected abstract void onLoop();
}
