package de.daver.buun.core.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SQLResultConsumer {

    void accept(ResultSet result) throws SQLException;

    static void iterateResult(ResultSet set, SQLResultConsumer entryConsumer) throws SQLException{
        while (set.next()) entryConsumer.accept(set);
    }

}
