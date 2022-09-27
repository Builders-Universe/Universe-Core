package de.daver.buun.core.world;

import de.daver.buun.core.world.gen.WorldGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

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
        return worlds.containsKey(id.toLowerCase());
    }

    public World getWorld(String id){
        return worlds.get(id.toLowerCase());
    }

    public Stream<World> getWorlds(){
        return this.worlds.values().stream();
    }

    public Stream<World> getWorlds(Predicate<World> filter){
        return getWorlds().filter(filter);
    }

    public Stream<World> getWorlds(WorldGenerator generator){
        return getWorlds(world -> world.getGenerator() == generator);
    }

    public Stream<World> getWorlds(boolean closed){
        return getWorlds(world -> world.getConfig().isClosed() == closed);
    }

    public Stream<World> getWorlds(UUID uuid){
        return getWorlds(world -> {
            if(world.getConfig().getOwner() == uuid) return true;
            if(world.getConfig().getModerators().contains(uuid)) return true;
            return world.getConfig().getMembers().contains(uuid);
        });
    }

}
