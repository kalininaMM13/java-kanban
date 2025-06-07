package TaskManager;

import Task.Epic;
import Task.SubTask;
import Task.Task;
import Task.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    TaskManager taskManager;
    Task task, task_2;
    Epic etask, etask_2;
    SubTask stask, stask_2;

    @BeforeEach
    void setUp() {
        taskManager = Managers.getDefault();
        task = new Task("1 task", "1t descr");
        task_2 = new Task("1 task", "1t descr");
        etask = new Epic("1 epic", "1e descr");
        etask_2 = new Epic("1 epic", "1e descr");
        stask = new SubTask("1 subtask", "1s descr", 1);
        stask_2 = new SubTask("1 subtask", "1s descr", 1);

    }

    @Test
    void addNewTask() {
        taskManager.addNewTask(task);
        assertEquals("1 task", task.getName());
        assertEquals(TaskStatus.NEW, task.getStatus());
        assertEquals("1t descr", task.getDescription());
    }

    @Test
    void addNewEpic() {
        taskManager.addNewTask(etask);
        assertEquals("1 epic", etask.getName());
        assertEquals(TaskStatus.NEW, etask.getStatus());
        assertEquals("1e descr", etask.getDescription());
    }

    @Test
    void addNewSubtask() {
        taskManager.addNewTask(etask);
        taskManager.addNewTask(stask);
        assertEquals("1 subtask", stask.getName());
        assertEquals(TaskStatus.NEW, stask.getStatus());
        assertEquals("1s descr", stask.getDescription());
    }

    @Test
    void getAllTasks() {
        taskManager.addNewTask(task);
        taskManager.addNewTask(task_2);
        assertEquals(2, taskManager.getAllTasks().size());
    }

    @Test
    void getAllEpic() {
        taskManager.addNewEpic(etask);
        taskManager.addNewEpic(etask_2);
        assertEquals(2, taskManager.getAllEpic().size());
    }

    @Test
    void getAllSubtasks() {
        taskManager.addNewEpic(etask);
        taskManager.addNewSubtask(stask);
        taskManager.addNewSubtask(stask_2);
        assertEquals(2, taskManager.getAllSubtasks().size());
        //попытаемся добавить сабтаску к несуществующему эпику
        SubTask badSubtask = new SubTask("bad subtask", "bad descr", 12);
        taskManager.addNewSubtask(badSubtask);
        assertEquals(2, taskManager.getAllSubtasks().size());
    }

    @Test
    void getTask() {
        taskManager.addNewTask(task);
        assertEquals(task, taskManager.getTask(task.getId()));
    }

    @Test
    void getEpicTask() {
        taskManager.addNewEpic(etask);
        assertEquals(etask, taskManager.getEpicTask(etask.getId()));
    }

    @Test
    void getSubTask() {
        taskManager.addNewEpic(etask);
        taskManager.addNewSubtask(stask);
        assertEquals(stask, taskManager.getSubTask(stask.getId()));
    }

    @Test
    void getHistory() {
        taskManager.addNewTask(task);
        taskManager.addNewEpic(etask);
        taskManager.addNewSubtask(stask);
        taskManager.getTask(task.getId());
        taskManager.getEpicTask(etask.getId());
        assertEquals(2, taskManager.getHistory().size());
        assertEquals(task, taskManager.getHistory().getFirst());
    }

    @Test
    void updateTask() {
        taskManager.addNewTask(task);
        task.setName("new task name");
        task.setDescription("new task description");
        task.setStatus(TaskStatus.IN_PROGRESS);
        taskManager.updateTask(task);
        assertEquals("new task name", task.getName());
        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
        assertEquals("new task description", task.getDescription());
    }

    @Test
    void updateEpicTask() {
        taskManager.addNewEpic(etask);
        etask.setName("new epic name");
        etask.setDescription("new epic description");
        taskManager.updateEpicTask(etask);
        assertEquals("new epic name", etask.getName());
        assertEquals(TaskStatus.NEW, etask.getStatus());
        assertEquals("new epic description", etask.getDescription());
    }

    @Test
        //!!!
    void updateSubTask() {
        taskManager.addNewEpic(etask);
        taskManager.addNewSubtask(stask);
        stask.setName("new subtask name");
        stask.setDescription("new subtask description");
        stask.setStatus(TaskStatus.IN_PROGRESS);
        taskManager.updateSubTask(stask);
        assertEquals("new subtask name", stask.getName());
        assertEquals(TaskStatus.IN_PROGRESS, stask.getStatus());
        assertEquals("new subtask description", stask.getDescription());
        assertEquals(TaskStatus.IN_PROGRESS, etask.getStatus());
    }

    @Test
    void removeTask() {
        taskManager.addNewTask(task);
        assertNotNull(taskManager.getTask(task.getId()));
        taskManager.removeTask(task.getId());
        assertNull(taskManager.getTask(task.getId()));
    }

    @Test
    void removeEpicTask() {
        taskManager.addNewEpic(etask);
        assertNotNull(taskManager.getEpicTask(etask.getId()));
        taskManager.removeEpicTask(etask.getId());
        assertNull(taskManager.getEpicTask(etask.getId()));
    }

    @Test
    void removeSubTask() {
        taskManager.addNewEpic(etask);
        taskManager.addNewSubtask(stask);
        assertNotNull(taskManager.getSubTask(stask.getId()));
        taskManager.removeSubTask(stask.getId());
        assertNull(taskManager.getSubTask(stask.getId()));
    }

    @Test
    void removeAllTasks() {
        taskManager.addNewTask(task);
        assertEquals(1, taskManager.getAllTasks().size());
        taskManager.removeAllTasks();
        assertEquals(0., taskManager.getAllTasks().size());
    }

    @Test
    void removeAllSubtasks() {
        taskManager.addNewEpic(etask);
        taskManager.addNewSubtask(stask);
        assertEquals(1, taskManager.getAllSubtasks().size());
        taskManager.removeAllSubtasks();
        assertEquals(0., taskManager.getAllSubtasks().size());
    }

    @Test
    void removeAllEpics() {
        taskManager.addNewEpic(etask);
        assertEquals(1, taskManager.getAllEpic().size());
        taskManager.removeAllEpics();
        assertEquals(0., taskManager.getAllEpic().size());
    }

    @Test
    void getEpicSubtasks() {
        taskManager.addNewEpic(etask);
        taskManager.addNewSubtask(stask);
        assertEquals(etask.getSubtasksId().getFirst(), taskManager.getEpicSubtasks(etask.getId()).getFirst().getId());
    }
}