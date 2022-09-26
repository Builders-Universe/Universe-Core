package de.daver.buun.core.world;

import de.daver.buun.core.warp.Warp;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WorldConfig {

    private final List<Warp> warps;
    private final List<UUID> moderators;
    private final List<UUID> members;
    private UUID owner;
    private String name;
    private boolean blockUpdates;
    private boolean closed;
    private Warp spawn;

    public WorldConfig(){
        this.moderators = new ArrayList<>();
        this.members = new ArrayList<>();
        this.warps = new ArrayList<>();
    }

    public boolean addMember(UUID uuid){
        if(members.contains(uuid)) return false;
        members.add(uuid);
        return true;
    }

    public boolean addModerator(UUID uuid){
        if(moderators.contains(uuid)) return false;
        moderators.add(uuid);
        return true;
    }

    public boolean removeMember(UUID uuid){
        return members.remove(uuid);
    }

    public boolean removeModerator(UUID uuid){
        return moderators.remove(uuid);
    }

    public WorldConfig setOwner(UUID owner){
        this.owner = owner;
        return this;
    }

    public WorldConfig setName(String name){
        this.name = name;
        return this;
    }

    public WorldConfig blockUpdates(boolean blocking){
        this.blockUpdates = blocking;
        return this;
    }

    public WorldConfig setClosed(boolean closed){
        this.closed = closed;
        return this;
    }

    public WorldConfig setSpawn(Warp spawn){
        this.spawn = spawn;
        return this;
    }

    public UUID getOwner(){
        return this.owner;
    }

    public String getName(){
        return this.name;
    }

    public boolean isBlockUpdates(){
        return this.blockUpdates;
    }

    public boolean isClosed(){
        return this.closed;
    }

    public Warp getSpawn(){
        return this.spawn;
    }

    public List<UUID> getMembers() {
        return members;
    }

    public List<UUID> getModerators() {
        return moderators;
    }

    public List<Warp> getWarps() {
        return warps;
    }
}
