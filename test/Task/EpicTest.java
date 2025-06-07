package Task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EpicTest {

    Epic etask;
    SubTask stask, stask_2;

    @BeforeEach
    void setUp() {
        etask = new Epic("1 epic", "1e descr");
        etask.setId(1);
        stask = new SubTask("1 subtask", "1s descr", 1);
        stask_2 = new SubTask("1 subtask", "1s descr", 1);
    }

    @Test
    void removeSubtask() {
        etask.addSubtask(stask.getId());
        etask.removeSubtask(stask.getId());
        assertEquals(0, etask.getSubtasksId().size());

    }

    @Test
    void clearSubtasks() {
        etask.addSubtask(stask.getId());
        etask.addSubtask(stask_2.getId());
        etask.clearSubtasks();
        assertEquals(0, etask.getSubtasksId().size());
    }

    @Test
    void getSubtasksId() {
        etask.addSubtask(stask.getId());
        assertEquals(etask.getSubtasksId().getFirst(), stask.getId());

    }

    @Test
    void addSubtask() {
        etask.addSubtask(stask.getId());
        assertEquals(etask.getSubtasksId().getFirst(), stask.getId());
    }
}