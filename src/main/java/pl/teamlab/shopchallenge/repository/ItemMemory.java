/**
 * (c) Piotr Plenik <piotr.plenik@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package pl.teamlab.shopchallenge.repository;

import org.springframework.stereotype.Component;
import pl.teamlab.shopchallenge.entity.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Singleton class
 *
 * @author jupeter
 */
@Component("itemRepository")
public class ItemMemory implements ItemDAO {

    private List<Item> items = new ArrayList<>();

    @Override
    public synchronized void add(Item item) {
        items.add(item);
    }

    @Override
    public synchronized void save(Item item) {
        Item current = this.get(item.getId());

        if(current == null) {
            items.add(item);
        } else {
            items.set(items.indexOf(current), item);
        }

    }

    @Override
    public void remove(Long id) {
        Item item = get(id);

        if(item != null) {
            items.remove(item);
        }
    }

    @Override
    public List<Item> findAll() {
        return items;
    }

    @Override
    public Item get(Long id) {
        Optional<Item> first = items
                .stream()
                .filter(i -> (i != null) && (i.getId().equals(id)))
                .findFirst();

        return first.orElse(null);
    }

    @Override
    public int count() {
        return items.size();
    }

}
