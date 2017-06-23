/**
 * (c) Piotr Plenik <piotr.plenik@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package pl.teamlab.shopchallenge.repository;

import org.junit.Test;
import pl.teamlab.shopchallenge.entity.Item;

import static org.junit.Assert.*;

/**
 * @author jupeter
 */
public class ItemMemoryTest {

    @Test
    public void testAddGet() {
        ItemDAO itemMemory = new ItemMemory();

        assertSame(0, itemMemory.count());

        Item item1 = new Item(1, "Name1", 10);
        itemMemory.add(item1);
        Item item2 = new Item(22, "Name2", 10);
        itemMemory.add(item2);

        assertSame(item1, itemMemory.get(1));
        assertSame(item2, itemMemory.get(22));

        assertNull(itemMemory.get(0));
    }

    @Test
    public void testSave() {
        ItemDAO itemMemory = new ItemMemory();
        Item item1 = new Item(1, "Name1", 10);
        item1.setName("Name2");
        item1.setQuantity(0);
        itemMemory.save(item1);

        assertSame("Name2", itemMemory.get(1).getName());
        assertSame(0, itemMemory.get(1).getQuantity());
        assertSame(1, itemMemory.count());

        item1.setQuantity(1);
        item1.setName("Name3");
        itemMemory.save(item1);
        assertSame("Name3", itemMemory.get(1).getName());
        assertSame(1, itemMemory.get(1).getQuantity());
        assertSame(1, itemMemory.count());

        itemMemory.save(new Item(2, "Name1", 10));
        assertSame(2, itemMemory.count());
    }

    @Test
    public void testRemove() {
        ItemDAO itemMemory = new ItemMemory();
        Item item1 = new Item(1, "Name1", 10);
        itemMemory.save(item1);
        assertSame(1, itemMemory.count());

        itemMemory.remove(0);
        assertSame(1, itemMemory.count());
        itemMemory.remove(1);
        assertSame(0, itemMemory.count());

    }
}
