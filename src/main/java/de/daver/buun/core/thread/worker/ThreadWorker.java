package de.daver.buun.core.thread.worker;

public abstract class ThreadWorker implements Runnable {

    private Thread thread;

    public void start(){
        if(thread != null) return;
        thread = new Thread(this);
        thread.start();
        this.onStart();
    }

    public void stop(){
        if(thread == null) return;
        this.onStop();
        thread.interrupt();
        thread = null;
    }

    protected abstract void onStart();
    protected abstract void onStop();

}
