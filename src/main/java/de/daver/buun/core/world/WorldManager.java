package de.daver.buun.core.world;

import de.daver.buun.core.world.gen.WorldGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class WorldManager {

    private final Map<String, World> worlds;
    private final World template;
    private final WorldGenerator standardGenerator;

    public WorldManager(WorldGenerator standardGenerator, Consumer<World> template){
        this.standardGenerator = standardGenerator;
        this.template = new World("template", standardGenerator);
        template.accept(this.template);
        this.worlds = new HashMap<>();
    }

    public World create(String id, WorldGenerator generator){
        if(worlds.containsKey(id.toLowerCase())) return null;
        World world = new World(id, generator);
        worlds.put(world.getId(), world);
        return world;
    }

    public boolean add(World world){
        if(worlds.containsKey(world.getId())) return false;
        worlds.put(world.getId(), world);
        return true;
    }

    public boolean delete(String id){
        return worlds.remove(id.toLowerCase()) != null;
    }

    public boolean contains(String id){

    }

}
