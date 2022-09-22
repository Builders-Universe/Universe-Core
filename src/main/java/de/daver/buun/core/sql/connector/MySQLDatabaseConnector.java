package de.daver.buun.core.sql.connector;

import de.daver.buun.core.exception.ExceptionHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDatabaseConnector implements DatabaseConnector{

    private final String url;
    private final String username;
    private final String password;

    public MySQLDatabaseConnector(String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Connection createConnection() {
        return new ExceptionHandler<Connection, SQLException, AutoCloseable>().accept(() -> DriverManager.getConnection(this.url, this.username, this.password));
    }
}
