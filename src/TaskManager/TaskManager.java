package TaskManager;

import Task.Epic;
import Task.SubTask;
import Task.Task;
import Task.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    int currentTaskId;
    HashMap<Integer, Task> kanbanTasks;

    public TaskManager() {
        this.kanbanTasks = new HashMap<Integer, Task>();
        currentTaskId = 0;
    }

    //2d. Создание. Сам объект должен передаваться в качестве параметра
    public void addNewTask(Task task) {
        if (task instanceof SubTask subTask) {
            if (kanbanTasks.containsKey(subTask.getEpicId())) {
                int taskId = setNewTaskId();
                task.setId(taskId);
                kanbanTasks.put(taskId, task);
                Epic prntEpic = (Epic) kanbanTasks.get(subTask.getEpicId());
                prntEpic.addSubtask(taskId);
            }
        } else {
            int taskId = setNewTaskId();
            task.setId(taskId);
            kanbanTasks.put(taskId, task);
        }
    }

    //2a. Получение списка всех-всех задач
    public ArrayList<Task> getKanbanAllTasks() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        for (Integer taskId : kanbanTasks.keySet()) {
            Task task = kanbanTasks.get(taskId);
            tasks.add(task);
        }
        return tasks;
    }

    //2a. Получение списка всех обычных задач
    public ArrayList<Task> getKanbanRegularTasks() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        for (Integer taskId : kanbanTasks.keySet()) {
            Task task = kanbanTasks.get(taskId);
            if (task.getClass().equals(Task.class)) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    //2a. Получение списка всех эпиков
    public ArrayList<Task> getKanbanEpicTasks() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        for (Integer taskId : kanbanTasks.keySet()) {
            Task task = kanbanTasks.get(taskId);
            if (task instanceof Epic) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    //2a. Получение списка всех сабтасок
    public ArrayList<Task> getKanbanSubTasks() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        for (Integer taskId : kanbanTasks.keySet()) {
            Task task = kanbanTasks.get(taskId);
            if (task instanceof SubTask) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    //2c. Получение по идентификатору
    public Task getTask(int taskId) {
        if (kanbanTasks.containsKey(taskId)) {
            return kanbanTasks.get(taskId);
        } else {
            return null;
        }
    }

    //2e. Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра
    public void updateTask(Task task) {
        if (kanbanTasks.containsKey(task.getId())) {
            kanbanTasks.put(task.getId(), task);
            if (task instanceof SubTask subTask) {
                Epic parentTask = (Epic) kanbanTasks.get(subTask.getEpicId());
                changeEpicStatus(parentTask);
            }
        }
    }

    // 2f. Удаление по идентификатору
    public void removeTask(int taskId) {
        Task task;
        if (kanbanTasks.containsKey(taskId)) {
            task = kanbanTasks.get(taskId);
        } else {
            return;
        }
        if (task instanceof SubTask subTask) {
            Epic parentTask = (Epic) kanbanTasks.get(subTask.getEpicId());
            parentTask.removeSubtask(subTask.getId());
            kanbanTasks.remove(subTask.getId());
            changeEpicStatus(parentTask);
        } else if (task instanceof Epic epicTask) {
            ArrayList<Integer> subTasks = epicTask.getSubtasksId();
            for (Integer subtask : subTasks) {
                kanbanTasks.remove(subtask);
            }
            kanbanTasks.remove(taskId);
        } else {
            kanbanTasks.remove(taskId);
        }
    }

    //2b. Удаляем все таски
    public void removeAllTask(int taskId) {
        kanbanTasks.clear();
    }

    //3a. Получение списка всех подзадач определённого эпика
    public ArrayList<Task> getEpicSubtasks(int taskId) {
        Task task;
        if (kanbanTasks.containsKey(taskId)) {
            task = kanbanTasks.get(taskId);
            if (task instanceof Epic epicTask) {
                ArrayList<Task> subTasks = new ArrayList<>();
                for (Integer subtask : epicTask.getSubtasksId()) {
                    subTasks.add(kanbanTasks.get(subtask));
                }
                return subTasks;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    //Генерируем новый айди таски
    private int setNewTaskId() {
        currentTaskId++;
        return currentTaskId;
    }

    private void changeEpicStatus(Epic etask) {
        int doneTask = 0;
        int newTask = 0;
        if (!etask.getSubtasksId().isEmpty()) {
            for (Integer subTaskId : etask.getSubtasksId()) {
                if (kanbanTasks.get(subTaskId).getStatus() == TaskStatus.DONE) {
                    doneTask++;
                }
                if (kanbanTasks.get(subTaskId).getStatus() == TaskStatus.NEW) {
                    newTask++;
                }
            }
            if (etask.getSubtasksId().size() == doneTask) {
                etask.setStatus(TaskStatus.DONE);
            } else if (etask.getSubtasksId().size() == newTask) {
                etask.setStatus(TaskStatus.NEW);
            } else {
                etask.setStatus(TaskStatus.IN_PROGRESS);
            }
        } else {
            etask.setStatus(TaskStatus.NEW);
        }
    }
}
