package de.daver.buun.core.sql;

import de.daver.buun.core.sql.command.SQLInserter;

public class Table {

    private TableCache cache;
    private final String name;
    private boolean cached;

    public Table(String name){
        this.name = name;
        cached = false;
    }

    public void setCached(boolean chached){
        this.cached = chached;
        if(chached) cache = new TableCache().load(name);
        else cache = null;
    }

    public void insert(SQLInserter inserter){

    }

    public void create(boolean exists){

    }
}
