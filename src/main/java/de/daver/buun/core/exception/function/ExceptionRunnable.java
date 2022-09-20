package de.daver.buun.core.exception.function;

public interface ExceptionRunnable<E extends Exception> {

    void run() throws E;

}
