package de.daver.buun.core.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SQLResultConsumer {

    void accept(ResultSet result) throws SQLException;

}
