package TaskManager;

import Task.Epic;
import Task.SubTask;
import Task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryHistoryManagerTest {

    @Test
    void getTaskHistory() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        Task task1 = new Task("1 task", "1t descr");
        Task task2 = new Task("2 task", "2t descr");
        Epic eptask1 = new Epic("1 epic", "1e descr");
        Epic eptask2 = new Epic("2 epic", "2e descr");
        SubTask sub12 = new SubTask("1 sub for epic 1", "1s e1 descr", 3);
        SubTask sub11 = new SubTask("2 sub for epic 1", "2s e1 descr", 3);
        SubTask sub2 = new SubTask("1 sub for epic 2", "1 s e2 descr", 6);
        Task task3 = new Task("3 task", "3t descr");
        Task task4 = new Task("4 task", "4t descr");
        Task task5 = new Task("5 task", "5t descr");
        Task task6 = new Task("6 task", "6t descr");
        historyManager.addTask(task1);
        historyManager.addTask(task2);
        historyManager.addTask(task3);
        historyManager.addTask(task4);
        historyManager.addTask(task5);
        historyManager.addTask(task6);
        historyManager.addTask(eptask1);
        historyManager.addTask(eptask2);
        historyManager.addTask(sub12);
        historyManager.addTask(sub11);
        historyManager.addTask(sub2);

        //размер истории = 10 - задачи добавились и не более 10 шт
        assertEquals(10, historyManager.getTaskHistory().size());
        //тк добавили больше 10, смотрим что на первом месте в листе теперь стоит таск2 из-за переполнения
        assertEquals("2 task", historyManager.getTaskHistory().getFirst().getName());

    }

    @Test
    void addTask() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        Task task1 = new Task("1 task", "1t descr");
        Task task2 = new Task("2 task", "2t descr");

        //история возвращает непустой список
        assertNotNull(historyManager.getTaskHistory());
    }


}