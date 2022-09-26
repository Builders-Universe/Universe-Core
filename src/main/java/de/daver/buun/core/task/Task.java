package de.daver.buun.core.task;

import de.daver.buun.core.world.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Task {

    private final String id;
    private final List<UUID> members;
    private final List<UUID> checkers;
    private UUID owner;
    private String name;
    private Location location;
    private Priority priority;
    private State state;

    public Task(String id){
        this.id = id.toLowerCase();
        this.members = new ArrayList<>();
        this.checkers = new ArrayList<>();
    }

    public Task copy(String id){
        Task newTask = new Task(id)
                .setName(this.name)
                .setLocation(this.location)
                .setOwner(this.owner)
                .setPriority(this.priority)
                .setState(this.state);
        newTask.members.addAll(members);
        newTask.checkers.addAll(checkers);
        return newTask;
    }

    public Task setName(String name){
        this.name = name;
        return this;
    }

    public Task addMember(UUID uuid){
        members.add(uuid);
        return this;
    }

    public Task removeMember(UUID uuid){
        members.remove(uuid);
        return this;
    }

    public Task addChecker(UUID uuid){
        checkers.add(uuid);
        return this;
    }

    public Task removeChecker(UUID uuid){
        checkers.remove(uuid);
        return this;
    }

    public Task setOwner(UUID uuid){
        owner = uuid;
        return this;
    }

    public Task setLocation(Location location){
        this.location = location;
        return this;
    }

    public Task setPriority(Priority priority){
        this.priority = priority;
        return this;
    }

    public Task setState(State state){
        this.state = state;
        return this;
    }

    public List<UUID> getMembers(){
        return this.members;
    }

    public List<UUID> getCheckers(){
        return this.checkers;
    }

    public UUID getOwner(){
        return this.owner;
    }

    public String getName(){
        return this.name;
    }

    public String getId(){
        return this.id;
    }

    public Priority getPriority(){
        return this.priority;
    }

    public State getState(){
        return this.state;
    }

    public Location getLocation(){
        return this.location;
    }

    enum Priority{
        LOW,
        MEDIUM,
        HIGH,
        URGENT;
    }

    enum State{
        CREATING,
        UNASSIGNED,
        IN_PROGRESS,
        CONTROLING,
        FINISHED,
        ARCHIVED;
    }

}
