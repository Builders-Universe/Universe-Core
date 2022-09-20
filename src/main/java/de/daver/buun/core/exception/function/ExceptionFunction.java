package de.daver.buun.core.exception.function;

public interface ExceptionFunction <T, E extends Exception, A extends AutoCloseable>{

    T run(A autocloseable) throws E;
}
