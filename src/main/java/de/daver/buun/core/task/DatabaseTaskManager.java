package de.daver.buun.core.task;

import de.daver.buun.core.sql.Database;
import de.daver.buun.core.world.Location;

import java.util.function.Consumer;

public class DatabaseTaskManager extends TaskManager {

    private final Database database;

    public DatabaseTaskManager(Consumer<Task> template, Database database) {
        super(template);
        this.database = database;
    }

    public void loadTasks(String tableName){
        database.enqeueAsync("SELECT * FROM " + tableName, set -> {
            //TODO Transformer
        });
    }

    public void createTaskTable(String tableName){
        database.execute("CREATE TABLE IF NOT EXISTS " + tableName + "");
        //TODO SQL Command
    }

    @Override
    public Task create(String id) {
        Task task = super.create(id);
        if(task == null) return null;
        database.enqeueAsync("INSERT");
        //TODO SQL Command
        return task;
    }

    @Override
    public boolean delete(String id) {
        if(!super.delete(id)) return false;
        database.enqeueAsync("DELETE");
        //TODO SQL Command
        return true;
    }

    public boolean modifyTask(String id, Consumer<Task> taskConsumer){
        Task task = getTask(id);
        if(task == null) return false;
        taskConsumer.accept(task);
        database.enqeueAsync("UPDATE");
        //TODO SQL Command
        return true;
    }
}
