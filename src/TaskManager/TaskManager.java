package TaskManager;

import Task.Epic;
import Task.SubTask;
import Task.Task;

import java.util.ArrayList;

public interface TaskManager {
    //2d. Создание таски. Сам объект должен передаваться в качестве параметра
    void addNewTask(Task task);

    //2d. Создание эпика. Сам объект должен передаваться в качестве параметра
    void addNewEpic(Epic task);

    //2d. Создание сабтаски. Сам объект должен передаваться в качестве параметра
    void addNewSubtask(SubTask task);

    //2a. Получение списка всех обычных задач
    ArrayList<Task> getAllTasks();

    //2a. Получение списка всех эпиков
    ArrayList<Epic> getAllEpic();

    //2a. Получение списка всех сабтасок
    ArrayList<SubTask> getAllSubtasks();

    //2c. Получение таски по идентификатору
    Task getTask(int taskId);

    //2c. Получение эпика по идентификатору
    Task getEpicTask(int taskId);

    //2c. Получение сабтаски по идентификатору
    Task getSubTask(int taskId);

    //2e. Обновление таски. Новая версия объекта с верным идентификатором передаётся в виде параметра
    void updateTask(Task task);

    //2e. Обновление эпика. Новая версия объекта с верным идентификатором передаётся в виде параметра
    void updateEpicTask(Epic task);

    //2e. Обновление сабтаски. Новая версия объекта с верным идентификатором передаётся в виде параметра
    void updateSubTask(SubTask task);

    // 2f. Удаление по идентификатору таски
    void removeTask(int taskId);

    // 2f. Удаление по идентификатору эпика, удаляем и его сабтаски
    void removeEpicTask(int taskId);

    // 2f. Удаление по идентификатору сабтаски
    void removeSubTask(int taskId);

    //2b. Удаляем все таски
    void removeAllTasks();

    //2b. Удаляем все сабтаски
    void removeAllSubtasks();

    //2b. Удаляем все эпики
    void removeAllEpics();

    //3a. Получение списка всех подзадач определённого эпика
    ArrayList<Task> getEpicSubtasks(int taskId);

    //Получение истории просмотров
    ArrayList<Task> getHistory();
}
