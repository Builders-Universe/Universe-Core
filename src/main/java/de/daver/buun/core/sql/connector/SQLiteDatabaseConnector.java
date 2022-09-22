package de.daver.buun.core.sql.connector;

import de.daver.buun.core.exception.ExceptionHandler;
import de.daver.buun.core.io.handler.FileHandler;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDatabaseConnector implements DatabaseConnector {

    private final String url;

    public SQLiteDatabaseConnector(File file){
        new FileHandler(file).create().overwrite(false).execute();
        url = "jdbc:sqlite:" + file.getAbsolutePath();
    }

    public SQLiteDatabaseConnector(String path){
        this(new File(path));
    }

    public SQLiteDatabaseConnector(File dir, String name){
        this(new File(dir, name));
    }

    @Override
    public Connection createConnection() {
        return new ExceptionHandler<Connection, SQLException, AutoCloseable>().accept(() -> DriverManager.getConnection(this.url));
    }
}
