package Teste.ds.list;

import ds.list.LinkedList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {

    @Test
    public void testAddAndToArray() {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(3, list.size());
        assertArrayEquals(new Integer[]{10, 20, 30}, list.toArray());
    }

    @Test
    public void testEmptyList() {
        LinkedList<Integer> list = new LinkedList<>();

        assertEquals(0, list.size());
        assertArrayEquals(new Integer[]{}, list.toArray());
    }

    @Test
    public void testRemoveAt() {
        LinkedList<String> list = new LinkedList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        String removed = list.removeAt(1);
        assertEquals("B", removed);
        assertEquals(2, list.size());
        assertArrayEquals(new String[]{"A", "C"}, list.toArray());
        list.removeAt(0); // Remove A
        assertEquals(1, list.size());
        assertArrayEquals(new String[]{"C"}, list.toArray());

        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAt(5));
    }

    @Test
    public void testGetAndSize() {
        LinkedList<Double> list = new LinkedList<>();
        list.add(1.1);
        list.add(2.2);

        assertEquals(2, list.size());
        assertEquals(1.1, list.get(0));
        assertEquals(2.2, list.get(1));

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));
    }
}