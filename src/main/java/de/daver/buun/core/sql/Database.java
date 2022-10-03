package de.daver.buun.core.sql;

import de.daver.buun.core.exception.ExceptionHandler;
import de.daver.buun.core.sql.connector.DatabaseConnector;
import de.daver.buun.core.sql.function.SQLResultTransformer;

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

    public Database startAsync(){
        asyncCommands.start();
        return this;
    }

    public Database stopAsync(){
        asyncCommands.stop();
        asyncCommands.clear();
        return this;
    }

    public Database connect(){
        if(this.connection != null) return this;
        connection = connector.createConnection();
        return this;
    }

    public Database disconnect(){
        if(this.connection == null) return this;
        new ExceptionHandler<>().print(true).run(connection::close);
        connection = null;
        asyncCommands.stop();
        return this;
    }

    public Database execute(String sql){
        new ExceptionHandler<>().print(true)
                .run(() -> connection.prepareStatement(sql).execute());
        return this;
    }

    public <T> T executeQuery(String sql, SQLResultTransformer<T> resultTransformer){
        return new ExceptionHandler<T, SQLException, AutoCloseable>().print(true)
                .accept(() -> resultTransformer.transform(connection.prepareStatement(sql).executeQuery()));
    }

    public Database enqeueAsync(String sql){
        if(!asyncCommands.isRunning()) startAsync();
        asyncCommands.enque(() -> this.execute(sql));
        return this;
    }

    public Database enqeueAsync(String sql, SQLResultConsumer consumer){
        if(!asyncCommands.isRunning()) startAsync();
        asyncCommands.enque(() ->  new ExceptionHandler<>().run(()-> consumer.accept(connection.prepareStatement(sql).executeQuery())));
        return this;
    }

    public SQLThread getThread(){
        return this.asyncCommands;
    }

}
