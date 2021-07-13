package tools;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Assertions {
    public static <Element> void assertSameElements(List<Element> expected, List<Element> actual, String message) {
        assertEquals(expected.size(), actual.size(), message);
        assertTrue(expected.containsAll(actual));
        assertTrue(actual.containsAll(expected));
    }

    public static <Element> void assertSameElements(List<Element> expected, List<Element> actual) {
        assertSameElements(expected, actual, null);
    }
}
