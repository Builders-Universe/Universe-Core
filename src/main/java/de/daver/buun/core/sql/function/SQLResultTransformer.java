package de.daver.buun.core.sql.function;

import java.sql.ResultSet;

public interface SQLResultTransformer<T> {

    T transform(ResultSet result);

}
