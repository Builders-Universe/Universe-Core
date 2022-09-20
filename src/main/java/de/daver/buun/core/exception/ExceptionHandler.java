package de.daver.buun.core.exception;

import de.daver.buun.core.exception.function.ExceptionConsumer;
import de.daver.buun.core.exception.function.ExceptionFunction;
import de.daver.buun.core.exception.function.ExceptionRunnable;
import de.daver.buun.core.exception.function.ExceptionSupplier;

import java.io.PrintStream;
import java.util.function.Function;
import java.util.function.Supplier;

public class ExceptionHandler <T, E extends Exception, A extends AutoCloseable> {

    private Function<E, T> function;
    private boolean print;
    private PrintStream outPut;
    private Supplier<A> resourceSupplier;

    public ExceptionHandler<T, E, A> handle(Function<E, T> function){
        this.function = function;
        return this;
    }

    public ExceptionHandler<T, E, A> output(PrintStream writer) {
        this.outPut = writer;
        return this;
    }

    public ExceptionHandler<T, E, A> print(boolean print){
        this.print = print;
        return this;
    }

    public ExceptionHandler<T, E, A> handleReturn(T returnValue){
        this.handle(e -> returnValue);
        return this;
    }

    public void run(ExceptionRunnable<E> runnable){
        try{
            runnable.run();
        }catch (Exception exception){
            handleException(exception);
        }
    }

    public T accept(ExceptionSupplier<T, E> supplier){
        try{
            return supplier.accept();
        }catch (Exception exception) {
            return handleException(exception);
        }
    }

    public ExceptionHandler<T, E, A> resource(Supplier<A> resourceSupplier) {
        this.resourceSupplier = resourceSupplier;
        return this;
    }

    public void run(ExceptionConsumer<E, A> consumer) {
        try (A auto = createAutoCloseable()) {
            consumer.accept(auto);
        } catch (Exception exception) {
            handleException(exception);
        }
    }

    public T accept(ExceptionFunction<T, E, A> function) {
        try (A auto = createAutoCloseable()) {
            return function.run(auto);
        } catch (Exception exception) {
            return handleException(exception);
        }
    }

    private A createAutoCloseable() {
        return (resourceSupplier == null) ? null : resourceSupplier.get();
    }

    private T handleException(Exception exception){
        printException(exception);
        return apply(exception);
    }

    private void printException(Exception exception){
        if(!print) return;
        if(outPut == null) exception.printStackTrace();
        else exception.printStackTrace(this.outPut);
    }

    @SuppressWarnings("unchecked")
    private T apply(Exception exception){
        if(function == null) return null;
        return function.apply((E) exception);
    }
}
