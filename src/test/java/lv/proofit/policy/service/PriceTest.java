package lv.proofit.policy.service;

import lv.proofit.policy.model.Price;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for {@link Price} class.
 */
class PriceTest {

    @Test
    void testEquals() {
        assertEquals(new Price("5"), new Price("5"));
        assertNotSame(new Price("5"), new Price("15"));
        assertNotSame(new Price("5"), null);
        assertNotSame(new Price("5"), Boolean.FALSE);

        final Price price = new Price("10");
        assertEquals(price, price);
    }

    @Test
    void testAdd() {
        final Price price1 = new Price("5");
        final Price price2 = new Price("1.77");

        price1.add(price2);
        assertEquals(new Price("6.77"), price1);
    }


}