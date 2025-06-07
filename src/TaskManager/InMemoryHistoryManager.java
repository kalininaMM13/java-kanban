package TaskManager;

import Task.Task;

import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {

    private final ArrayList<Task> taskHistory;

    public InMemoryHistoryManager() {
        this.taskHistory = new ArrayList<Task>(10);
    }

    @Override
    public ArrayList<Task> getTaskHistory() {
        return taskHistory;
    }

    @Override
    public void addTask(Task task) {
        if (taskHistory.size() == 10) {
            taskHistory.remove(0);
        }
        taskHistory.add(task);
    }

}
