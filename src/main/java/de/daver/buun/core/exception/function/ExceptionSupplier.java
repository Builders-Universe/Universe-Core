package de.daver.buun.core.exception.function;

public interface ExceptionSupplier <T, E extends Exception>{

    T accept() throws E;

}
