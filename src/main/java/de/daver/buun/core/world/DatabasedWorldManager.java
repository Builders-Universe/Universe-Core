package de.daver.buun.core.world;

import de.daver.buun.core.sql.Database;
import de.daver.buun.core.world.gen.WorldGenerator;

import java.util.function.Consumer;

public class DatabasedWorldManager extends WorldManager{

    private final Database database;

    public DatabasedWorldManager(Database database, WorldGenerator standardGenerator, Consumer<World> template) {
        super(standardGenerator, template);
        this.database = database;
    }

    public void loadWorlds(String tableName){
        database.enqeueAsync("SELECT * FROM " + tableName);
        //TODO ResultConsumer
    }

    public void createWorldsTable(String tableName){
        database.enqeueAsync("CREATE TABLE IF NOT EXISTS" + tableName);
        //TODO SQL command
    }


}
