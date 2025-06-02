package Task;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subtasks;

    public Epic(String name, String description) {
        super(name, description);
        subtasks = new ArrayList<>();

    }

    public void removeSubtask(int subtaskId) {
        if (!subtasks.isEmpty()) {
            subtasks.remove(subtasks.remove(subtasks.indexOf(subtaskId)));
        }

    }

    public void clearSubtasks() {
        subtasks.clear();
    }

    @Override
    public String toString() {
        String tmp = super.toString() + ", subtasks: ";
        if (subtasks.size() > 0) {
            for (Integer subtask : subtasks) {
                tmp = tmp + " (" + subtask + ")";
            }
        } else {
            tmp = tmp + " (empty)";
        }

        return tmp;
    }

    public ArrayList<Integer> getSubtasksId() {
        return subtasks;
    }

    public void addSubtask(int subtaskId) {
        subtasks.add(subtaskId);
    }

}

