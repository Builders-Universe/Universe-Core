package de.daver.buun.core.util;

import java.util.function.Consumer;

public class Result <T> {

    private final T value;
    private final int code;

    public Result(T value, int code){
        this.value = value;
        this.code = code;
    }

    public T get(){
        return this.value;
    }

    public int getCode(){
        return this.code;
    }

    public boolean isPresent(){
        return this.value != null;
    }

    public Result<T> ifPresent(Consumer<T> consumer){
        if(isPresent()) consumer.accept(this.value);
        return this;
    }

    public Result<T> elseCase(Runnable runnable){
        if(!isPresent()) runnable.run();
        return this;

    }
}
