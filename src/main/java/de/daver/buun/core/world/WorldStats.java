package de.daver.buun.core.world;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WorldStats {

    private long created;
    private Map<UUID, Long> lastSeen;
    private Map<UUID, Long> userTime;

    public WorldStats(){
        lastSeen = new HashMap<>();
        userTime = new HashMap<>();
        created = System.currentTimeMillis();
    }

    public WorldStats setCreated(long unixTime){
        this.created = unixTime;
        return this;
    }

    public WorldStats setUserTime(UUID uuid, long duration){
        this.userTime.put(uuid, duration);
        return this;
    }

    public WorldStats addUserTime(UUID uuid, long duration){
        if(!this.userTime.containsKey(uuid)) setUserTime(uuid, duration);
        else this.userTime.put(uuid, this.userTime.get(uuid) + duration);
        return this;
    }

    public WorldStats setLastSeen(UUID uuid, long unixTime){
        this.lastSeen.put(uuid, unixTime);
        return this;
    }

    public long getCreated(){
        return this.created;
    }

    public Map<UUID, Long> getLastSeen(){
        return this.lastSeen;
    }

    public Map<UUID, Long> getUserTime() {
        return userTime;
    }
}
