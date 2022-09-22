package de.daver.buun.core.reflect;

import de.daver.buun.core.exception.ExceptionHandler;

import java.lang.reflect.Field;

public class FieldReflection<V> {

    private final Field field;

    public FieldReflection(Class<?> clazz, String fieldName){
        field = (Field) new ExceptionHandler<>().accept(() -> clazz.getDeclaredField(fieldName));
        field.setAccessible(true);
    }

    @SuppressWarnings("unchecked")
    public V get(Object object){
        return (V) new ExceptionHandler<>().accept(() -> field.get(object));
    }

    public void set(Object object, V value){
        new ExceptionHandler<>().run(() -> field.set(object, value));
    }
}
