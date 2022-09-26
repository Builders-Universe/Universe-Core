package de.daver.buun.core.sql;

import de.daver.buun.core.exception.ExceptionHandler;
import de.daver.buun.core.sql.connector.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.function.Function;

public class Database {

    private final DatabaseConnector connector;
    private Connection connection;
    private final SQLThread asyncCommands;

    public Database(DatabaseConnector  connector){
        this.connector = connector;
        this.asyncCommands = new SQLThread();
    }

    public void startAsync(){
        asyncCommands.start();
    }

    public void stopAsync(){
        asyncCommands.stop();
        asyncCommands.clear();
    }

    public void connect(){
        if(this.connection != null) return;
        connection = connector.createConnection();
    }

    public void disconnect(){
        if(this.connection == null) return;
        new ExceptionHandler<>().print(true).run(connection::close);
        connection = null;
        asyncCommands.stop();
    }

    public void execute(String sql){
        new ExceptionHandler<>().print(true)
                .run(() -> connection.prepareStatement(sql).execute());
    }

    public <T> T executeQuery(String sql, Function<ResultSet, T> resultTransformer){
        return new ExceptionHandler<T, SQLException, AutoCloseable>().print(true)
                .accept(() -> resultTransformer.apply(connection.prepareStatement(sql).executeQuery()));
    }

    public void enqeueAsync(String sql){
        if(!asyncCommands.isRunning()) startAsync();
        asyncCommands.enque(() -> this.execute(sql));
    }

    public void enqeueAsync(String sql, Consumer<ResultSet> consumer){
        if(!asyncCommands.isRunning()) startAsync();
        asyncCommands.enque(() ->  new ExceptionHandler<>().run(()-> consumer.accept(connection.prepareStatement(sql).executeQuery())));
    }

    public SQLThread getThread(){
        return this.asyncCommands;
    }

}
