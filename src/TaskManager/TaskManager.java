package TaskManager;

import Task.Epic;
import Task.SubTask;
import Task.Task;
import Task.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private int currentTaskId;
    //HashMap<Integer, Task> kanbanTasks;
    HashMap<Integer, Task> tasks;
    HashMap<Integer, Epic> epicTasks;
    HashMap<Integer, SubTask> subTasks;

    public TaskManager() {
        //this.kanbanTasks = new HashMap<Integer, Task>();
        this.tasks = new HashMap<Integer, Task>();
        this.epicTasks = new HashMap<Integer, Epic>();
        this.subTasks = new HashMap<Integer, SubTask>();
        currentTaskId = 0;
    }

    //2d. Создание таски. Сам объект должен передаваться в качестве параметра
    public void addNewTask(Task task) {
        int taskId = setNewTaskId();
        task.setId(taskId);
        tasks.put(taskId, task);
    }

    //2d. Создание эпика. Сам объект должен передаваться в качестве параметра
    public void addNewEpic(Epic task) {
        int taskId = setNewTaskId();
        task.setId(taskId);
        epicTasks.put(taskId, task);
    }

    //2d. Создание сабтаски. Сам объект должен передаваться в качестве параметра
    public void addNewSubtask(SubTask task) {
        if (epicTasks.containsKey(task.getEpicId())) {
            int taskId = setNewTaskId();
            task.setId(taskId);
            subTasks.put(taskId, task);
            Epic prntEpic = epicTasks.get(task.getEpicId());
            prntEpic.addSubtask(taskId);
        }
    }

    //2a. Получение списка всех обычных задач
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<Task>(tasks.values());
    }

    //2a. Получение списка всех эпиков
    public ArrayList<Epic> getAllEpic() {
        return new ArrayList<Epic>(epicTasks.values());
    }

    //2a. Получение списка всех сабтасок
    public ArrayList<SubTask> getAllSubtasks() {
        return new ArrayList<SubTask>(subTasks.values());
    }

    //2c. Получение таски по идентификатору
    public Task getTask(int taskId) {
        if (tasks.containsKey(taskId)) {
            return tasks.get(taskId);
        } else {
            return null;
        }
    }

    //2c. Получение эпика по идентификатору
    public Task getEpicTask(int taskId) {
        if (epicTasks.containsKey(taskId)) {
            return epicTasks.get(taskId);
        } else {
            return null;
        }
    }

    //2c. Получение сабтаски по идентификатору
    public Task getSubTask(int taskId) {
        if (subTasks.containsKey(taskId)) {
            return subTasks.get(taskId);
        } else {
            return null;
        }
    }

    //2e. Обновление таски. Новая версия объекта с верным идентификатором передаётся в виде параметра
    public void updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        }
    }

    //2e. Обновление эпика. Новая версия объекта с верным идентификатором передаётся в виде параметра
    public void updateEpicTask(Epic task) {
        if (epicTasks.containsKey(task.getId())) {
            epicTasks.put(task.getId(), task);
        }
    }

    //2e. Обновление сабтаски. Новая версия объекта с верным идентификатором передаётся в виде параметра
    public void updateSubTask(SubTask task) {
        if (subTasks.containsKey(task.getId())) {
            subTasks.put(task.getId(), task);
            Epic prntEpic = epicTasks.get(task.getEpicId());
            changeEpicStatus(prntEpic);
        }
    }

    // 2f. Удаление по идентификатору таски
    public void removeTask(int taskId) {
        if (tasks.containsKey(taskId)) {
            tasks.remove(taskId);
        }
    }

    // 2f. Удаление по идентификатору эпика, удаляем и его сабтаски
    public void removeEpicTask(int taskId) {
        if (epicTasks.containsKey(taskId)) {
            ArrayList<Integer> subTasks = epicTasks.get(taskId).getSubtasksId();
            for (Integer subtask : subTasks) {
                subTasks.remove(subtask);
            }
            epicTasks.remove(taskId);
        }
    }

    // 2f. Удаление по идентификатору сабтаски
    public void removeSubTask(int taskId) {
        if (subTasks.containsKey(taskId)) {
            SubTask task = subTasks.get(taskId);
            Epic prntEpic = epicTasks.get(task.getEpicId());
            prntEpic.removeSubtask(task.getId());
            subTasks.remove(task.getId());
            changeEpicStatus(prntEpic);
        }
    }

    //2b. Удаляем все таски
    public void removeAllTasks() {
        tasks.clear();
    }

    //2b. Удаляем все сабтаски
    public void removeAllSubtasks() {
        subTasks.clear();
        for (Epic epictask : epicTasks.values()) {
            epictask.clearSubtasks();
            changeEpicStatus(epictask);
        }
    }

    //2b. Удаляем все эпики
    public void removeAllEpics() {
        for (Epic epictask : epicTasks.values()) {
            ArrayList<Integer> subTasksId = epictask.getSubtasksId();
            for (Integer subtask : subTasksId) {
                subTasks.remove(subtask);
            }
        }
        epicTasks.clear();
    }

    //3a. Получение списка всех подзадач определённого эпика
    public ArrayList<Task> getEpicSubtasks(int taskId) {
        if (epicTasks.containsKey(taskId)) {
            ArrayList<Task> subTasks = new ArrayList<>();
            for (Integer subtask : epicTasks.get(taskId).getSubtasksId()) {
                subTasks.add(subTasks.get(subtask));
            }
            return subTasks;
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
                if (subTasks.get(subTaskId).getStatus() == TaskStatus.DONE) {
                    doneTask++;
                }
                if (subTasks.get(subTaskId).getStatus() == TaskStatus.NEW) {
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
