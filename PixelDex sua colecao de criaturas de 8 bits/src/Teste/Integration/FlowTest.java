package Teste.Integration;

import core.PixelCollection;
import core.PixelIndex;
import domain.Pixel;
import domain.Raridade;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FlowTest {

    @Test
    void testAddAndFind() {
        PixelCollection collection = new PixelCollection();
        PixelIndex index = new PixelIndex();

        Pixel p1 = new Pixel(101, "picozinho", Raridade.COMUM, 12);
        Pixel p2 = new Pixel(202, "drakonauta", Raridade.RARO, 87);

        collection.add(p1);
        collection.add(p2);
        index.add(p1);
        index.add(p2);

        Pixel result = index.searchByName("picozinho");
        assertNotNull(result);
        assertEquals(101, result.getId());

        result = index.searchById(202);
        assertNotNull(result);
        assertEquals("drakonauta", result.getName());

        assertEquals(2, index.size());
    }

    @Test
    void testRemoveSync() {
        PixelCollection collection = new PixelCollection();
        PixelIndex index = new PixelIndex();

        Pixel p = new Pixel(500, "zorblax", Raridade.EPICO, 99);

        collection.add(p);
        index.add(p);

        index.remove("zorblax");

        assertNull(index.searchByName("zorblax"));
        assertNull(index.searchById(500));
        assertEquals(0, index.size());
    }

}