package de.daver.buun.core.task;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TaskManager {

    private final Map<String, Task> tasks;
    private final Task template;

    public TaskManager(Consumer<Task> template){
        this.tasks = new HashMap<>();
        this.template = new Task("template");
        if(template == null) return;
        template.accept(this.template);
    }

    public Task create(String id){
        if(contains(id)) return null;
        Task newTask = template.copy(id);
        tasks.put(newTask.getId(), newTask);
        return newTask;
    }

    public boolean addTask(Task task){
        if(task == null) return false;
        if(contains(task.getId())) return false;
        tasks.put(task.getId(), task);
        return true;
    }

    public boolean delete(String id){
        return tasks.remove(id.toLowerCase()) != null;
    }

    public boolean contains(String id){
        return tasks.containsKey(id.toLowerCase());
    }

    public Task getTask(String id){
        return tasks.get(id.toLowerCase());
    }

    public Stream<Task> getTasks(){
        return this.tasks.values().stream();
    }

    public Stream<Task> getTasks(Predicate<Task> filter){
        return getTasks().filter(filter);
    }

    public Stream<Task> getTasks(Task.Priority priority){
        return getTasks(task -> task.getPriority() == priority);
    }

    public Stream<Task> getTasks(Task.State state){
        return getTasks(task -> task.getState() == state);
    }

    public Stream<Task> getTasks(UUID uuid){
        return getTasks(task -> {
            if(task.getOwner() == uuid) return true;
            if(task.getCheckers().contains(uuid)) return true;
            return task.getMembers().contains(uuid);
        });
    }
}
