package Teste.ds.tree;

import domain.Pixel;
import domain.Raridade;
import ds.tree.BST;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BSTTest {
    private BST<Pixel> bst;
    public static final Comparator<Pixel> BY_NAME_THEN_ID = (a, b) -> {
        int nameCompare = a.getName().compareToIgnoreCase(b.getName());
        if (nameCompare != 0) {
            return nameCompare;
        }
        return Integer.compare(a.getId(), b.getId());
    };
    @BeforeEach
    void setUp() {
        bst = new BST<>(BY_NAME_THEN_ID);
        bst.insert(new Pixel(101, "LAIS", Raridade.COMUM, 10));
        bst.insert(new Pixel(202, "LETICIA", Raridade.RARO, 20));
        bst.insert(new Pixel(303, "BRUNO", Raridade.LENDARIO, 30));
        bst.insert(new Pixel(404, "KARINA", Raridade.EPICO, 25));
        bst.insert(new Pixel(505, "DONA VERA", Raridade.LENDARIO, 15));
    }
    @Test
    void testInsertAndSize() {
        assertEquals(5, bst.size());
        bst.insert(new Pixel(606, "Zulu", Raridade.RARO, 50));
        assertEquals(6, bst.size());
    }
    @Test
    void testInOrderTraversal() {
        List<Pixel> result = bst.inOrder();

        assertEquals("Ariel", result.get(0).getName());
        assertEquals(101, result.get(0).getId());
        assertEquals("Ariel", result.get(1).getName());
        assertEquals(505, result.get(1).getId());
        assertEquals("Bob", result.get(2).getName());
        assertEquals("Camilo", result.get(4).getName());
        assertEquals(5, result.size());
    }

    @Test
    void testSearchByName_Found() {
        Pixel keyPixel = new Pixel(-1, "Bob", Raridade.COMUM, 0);
        Pixel found = bst.search(keyPixel);
        assertNotNull(found);
        assertEquals("Bob", found.getName());
        assertTrue(found.getId() == 202 || found.getId() == 404);
    }

    @Test
    void testSearchByName_NotFound() {
        Pixel keyPixel = new Pixel(-1, "Fantasma", Raridade.COMUM, 0);
        Pixel found = bst.search(keyPixel);
        assertNull(found);
    }

    @Test
    void testRemoveByName_Existing() {
        Pixel keyPixel = new Pixel(-1, "Ariel", Raridade.COMUM, 0);
        Pixel removed = bst.remove(keyPixel);
        assertNotNull(removed);
        assertEquals("Ariel", removed.getName());
        assertEquals(4, bst.size());
        Pixel found = bst.search(keyPixel);
        assertNotNull(found);
        assertEquals("Ariel", found.getName());
        assertEquals(505, found.getId());
    }
    @Test
    void testRemoveByName_NonExisting() {
        Pixel keyPixel = new Pixel(-1, "Xena", Raridade.COMUM, 0);
        Pixel removed = bst.remove(keyPixel);
        assertNull(removed);
        assertEquals(5, bst.size());
    }
    @Test
    void testRangeQuery() {
        Pixel start = new Pixel(-1, "Ariel", Raridade.COMUM, 0);
        Pixel end = new Pixel(Integer.MAX_VALUE, "Bob", Raridade.COMUM, 0);
        List<Pixel> result = bst.range(start, end);
        assertEquals(4, result.size());
        assertEquals("Ariel", result.get(0).getName());
        assertEquals("Bob", result.get(3).getName());
    }
    @Test
    void testRangeQuery_OnlyCamilo() {
        Pixel start = new Pixel(-1, "Camilo", Raridade.COMUM, 0);
        Pixel end = new Pixel(Integer.MAX_VALUE, "Camilo", Raridade.COMUM, 0);
        List<Pixel> result = bst.range(start, end);
        assertEquals(1, result.size());
        assertEquals("Camilo", result.get(0).getName());
    }
}