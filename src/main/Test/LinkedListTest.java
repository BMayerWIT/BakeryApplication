import Resources.LinkedList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {
    LinkedList<Object> days = new LinkedList<>();
    LinkedList<Object> months = new LinkedList<>();

    @BeforeEach
    void setUp() {
        days.add("Monday");
        days.add("Tuesday");
    }

    @AfterEach
    void tearDown() {
        days.remove();
    }

    @Test
    void getHeadTest() {
        assertEquals("Monday", days.get(0));

    }

    @Test
    void AddNodeTest() {
        assertNull(days.get(2));//in 0 and 1 index
        days.add("Sunday");
        assertEquals("Sunday", days.get(2));//now should be in 2
    }

    @Test
    void getTest() {
        assertEquals(days.get(0), days.get(0));
        assertNull(months.get(0));
        assertEquals("Tuesday", days.get(1));
    }

    @Test
    void deleteAllTest() {
        assertEquals(days.get(0), days.get(0));
        days.remove();
        assertNull(days.get(0));

    }

    @Test
    void listSizeTest() {
        assertEquals(2, days.numberOfNodes());
        assertEquals(0, months.numberOfNodes());
    }
}



