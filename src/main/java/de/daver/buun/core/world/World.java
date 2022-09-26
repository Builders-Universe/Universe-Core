package de.daver.buun.core.world;

import de.daver.buun.core.world.gen.WorldGenerator;

import java.util.function.Consumer;


public class World {

    private final String id;
    private final WorldGenerator generator;
    private final WorldConfig config;
    private final WorldStats stats;
    private boolean loaded;

    public World(String id, WorldGenerator generator){
        this.id = id.toLowerCase();
        this.generator = generator;
        this.loaded = false;
        this.config = new WorldConfig();
        this.stats = new WorldStats();
    }

    public World copy(String id, WorldGenerator generator){
        World newWorld = new World(id, generator);
        return newWorld.modifyConfig(worldConfig ->  {
            //TODO MODIFY CONFIG
        }).modifyStats(worldStats ->  {
            //TODO MODFIY STATS
        }).setLoaded(loaded);
    }

    public World modifyConfig(Consumer<WorldConfig> configConsumer){
        configConsumer.accept(this.config);
        return this;
    }

    public World modifyStats(Consumer<WorldStats> statsConsumer){
        statsConsumer.accept(this.stats);
        return this;
    }

    public World setLoaded(boolean loaded){
        this.loaded = loaded;
        return this;
    }

    public String getId(){
        return this.id;
    }

    public WorldGenerator getGenerator(){
        return this.generator;
    }

    public WorldConfig getConfig(){
        return this.config;
    }

    public WorldStats getStats(){
        return this.stats;
    }

    public boolean isLoaded(){
        return this.loaded;
    }
}
