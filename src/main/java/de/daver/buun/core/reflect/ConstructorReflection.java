package de.daver.buun.core.reflect;

import de.daver.buun.core.exception.ExceptionHandler;

import java.lang.reflect.Constructor;

public class ConstructorReflection<T> {

    private final Constructor<T> constructor;

    public ConstructorReflection(Class<T> clazz, Class<?>...arguments){
        this.constructor = new ExceptionHandler<Constructor<T>, Exception, AutoCloseable>().accept(() -> clazz.getDeclaredConstructor(arguments));
        this.constructor.setAccessible(true);
    }

    public T newInstance(Object...arguments){
        return new ExceptionHandler<T, Exception, AutoCloseable>().accept(() -> constructor.newInstance(arguments));
    }

}
