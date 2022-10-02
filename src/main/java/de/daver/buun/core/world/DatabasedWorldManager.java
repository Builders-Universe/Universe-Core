package de.daver.buun.core.world;

import de.daver.buun.core.sql.Database;
import de.daver.buun.core.sql.SQLResultConsumer;
import de.daver.buun.core.world.gen.WorldGenerator;

import java.sql.ResultSet;
import java.util.function.Consumer;

public class DatabasedWorldManager extends WorldManager{

    private final Database database;

    public DatabasedWorldManager(Database database, WorldGenerator standardGenerator, Consumer<World> template) {
        super(standardGenerator, template);
        this.database = database;
    }

    public void loadWorlds(String tableName){
        database.enqeueAsync("SELECT * FROM " + tableName,
                result -> SQLResultConsumer.iterateResult(result,
                        set -> add(resultToWorld(set))));
    }

    private World resultToWorld(ResultSet result){
        return new World("", null);
    }

    public void createWorldsTable(String tableName){
        database.enqeueAsync("CREATE TABLE IF NOT EXISTS" + tableName);
        //TODO SQL command
    }

    @Override
    public boolean delete(String id) {
        boolean result = super.delete(id);
        if(!result) return false;
        database.enqeueAsync("DELETE");
        //TODO SQL-Command
        return true;
    }

    @Override
    public World create(String id, WorldGenerator generator) {
        World world = super.create(id, generator);
        if(world == null) return null;
        database.enqeueAsync("INSERT");
        //TODO SQL-Command
        return world;
    }

    public boolean modify(String id, Consumer<World> worldConsumer){
        World world = getWorld(id);
        if(world == null) return false;
        worldConsumer.accept(world);
        database.enqeueAsync("UPDATE");
        //TODO SQL-Command
        return true;
    }
}
