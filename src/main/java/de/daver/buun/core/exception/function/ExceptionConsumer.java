package de.daver.buun.core.exception.function;

public interface ExceptionConsumer <E extends Exception, A extends AutoCloseable> {

    void accept(A autocloseable) throws E;

}
