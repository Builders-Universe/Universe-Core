package de.daver.buun.core.sql.connector;

import java.sql.Connection;

public interface DatabaseConnector {

    Connection createConnection();

}
