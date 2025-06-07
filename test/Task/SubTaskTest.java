package Task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubTaskTest {

    Epic etask;
    SubTask stask;

    @BeforeEach
    void setUp() {
        etask = new Epic("1 epic", "1e descr");
        stask = new SubTask("1 subtask", "1s descr", 1);
    }

    @Test
    void getEpicId() {
        assertEquals(1, stask.getEpicId());
    }
}