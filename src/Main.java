/// ///
/// Привет!
/// Я все поправила на 3 разных хэш мапа, но разве в одном не было логичнее оставить?
///     (В ТЗ это явно не указано, а предлагается как вариант решения в "подсказках")
/// Оно все работало и так, а фронтовой команде при будущей разработке было бы проще? :)
///     Им всегда нужно было бы обращаться к одной и той же структуре, а дальше уже была бы обработка на бэке,
///     чтобы разобраться, какой прилетел тип задачи и что с ней нужно делать.
/// Да, мне сложнее в данный момент, но зато всему проекту удобнее? :)
/// (А еще не нужно дублировать код каждый раз по 3 раза для почти одинаковых методов по каждому пункту ТЗ :D)
/// ///

import Task.Epic;
import Task.SubTask;
import Task.Task;
import Task.TaskStatus;
import TaskManager.TaskManager;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("1 task", "1t descr");
        Task task2 = new Task("2 task", "2t descr");
        Epic eptask1 = new Epic("1 epic", "1e descr");
        Epic eptask2 = new Epic("2 epic", "2e descr");
        SubTask sub12 = new SubTask("1 sub for epic 1", "1s e1 descr", 3);
        SubTask sub11 = new SubTask("2 sub for epic 1", "2s e1 descr", 3);
        SubTask sub2 = new SubTask("1 sub for epic 2", "1 s e2 descr", 6);
        taskManager.addNewTask(task1);
        taskManager.addNewTask(task2);
        taskManager.addNewEpic(eptask1);
        taskManager.addNewSubtask(sub12);
        taskManager.addNewSubtask(sub11);
        taskManager.addNewEpic(eptask2);
        taskManager.addNewSubtask(sub2);

        //-------------
        System.out.println("Печатаем таски, эпики и сабтаски");
        System.out.println(taskManager.getAllTasks());
        System.out.println(taskManager.getAllEpic());
        System.out.println(taskManager.getAllSubtasks());

        //-------------
        System.out.println("Изменяем статус таски и сабтаски, эпик меняется сам");
        Task task1n = new Task("new_stat 1 task", "1t descr");
        task1n.setStatus(TaskStatus.DONE);
        task1n.setId(1);
        SubTask sub12n = new SubTask("new_stat 1 sub for epic 1", "1s e1 descr", 3);
        sub12n.setStatus(TaskStatus.DONE);
        sub12n.setId(4);

        taskManager.updateTask(task1n);
        taskManager.updateSubTask(sub12n);

        System.out.println(taskManager.getTask(1));
        System.out.println(taskManager.getSubTask(4));
        System.out.println(taskManager.getEpicTask(3));

        //--------------
        System.out.println("Удаляем задачу из эпика, смотрим сабтаски эпика");
        taskManager.removeSubTask(7);
        System.out.println(taskManager.getEpicTask(6));

    }
}