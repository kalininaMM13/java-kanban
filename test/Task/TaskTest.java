package Task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TaskTest {

    @Test
    void testHashCode() {
        Task task1 = new Task("task1", "description1");
        Task task2 = new Task("task2", "description2");
        task1.setId(1);
        task2.setId(1);
        assertEquals(task1.hashCode(), task2.hashCode());
        task2.setId(2);
        assertNotEquals(task1.hashCode(), task2.hashCode());

    }

    @Test
    void testEquals() {
        Task task1 = new Task("task1", "description1");
        Task task2 = new Task("task2", "description2");
        task1.setId(1);
        task2.setId(1);
        assertEquals(task1, task2);
        task2.setId(2);
        assertNotEquals(task1, task2);
    }
}