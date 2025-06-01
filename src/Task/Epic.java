package Task;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subtasks;

    public Epic(String name, String description) {
        super(name, description);
        subtasks = new ArrayList<>();

    }

    public void removeSubtask(int subtaskId) {
        System.out.println("*хотим удалить*" + subtaskId);
        String tmp = new String("");
        for (Integer subtask : subtasks) {
            tmp = tmp + " (" + subtask + ")";
        }
        System.out.println("*вот что внутри*" + tmp);
        System.out.println("*айди нужного*" + subtasks.indexOf(subtaskId));
        if (!subtasks.isEmpty()) {
            subtasks.remove(subtasks.remove(subtasks.indexOf(subtaskId)));
        }

    }

    @Override
    public String toString() {
        String tmp = super.toString() + ", subtasks: ";
        for (Integer subtask : subtasks) {
            tmp = tmp + " (" + subtask + ")";
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

