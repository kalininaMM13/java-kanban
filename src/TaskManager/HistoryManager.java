package TaskManager;

import Task.Task;

import java.util.ArrayList;

public interface HistoryManager {
    ArrayList<Task> getTaskHistory();

    void addTask(Task task);
}
