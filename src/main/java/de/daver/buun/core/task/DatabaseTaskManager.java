package de.daver.buun.core.task;

import de.daver.buun.core.sql.Database;
import de.daver.buun.core.world.Location;

public class DatabaseTaskManager extends TaskManager {

    private final Database database;

    public DatabaseTaskManager(Location standard, Database database) {
        super(task -> task.setState(Task.State.CREATING)
                .setPriority(Task.Priority.LOW)
                .setLocation(standard));
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
        database.enqeueAsync(""); //TODO SQL Command

        return task;
    }
}
