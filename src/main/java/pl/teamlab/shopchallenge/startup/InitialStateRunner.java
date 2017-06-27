/**
 * (c) Piotr Plenik <piotr.plenik@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package pl.teamlab.shopchallenge.startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.teamlab.shopchallenge.entity.Item;
import pl.teamlab.shopchallenge.repository.ItemDAO;


@Component
public class InitialStateRunner implements CommandLineRunner {
    @Autowired
    private ItemDAO itemRepository;
    
    @Override
    public void run(String... args) throws Exception {
        itemRepository.add(new Item(1L, "Item A", 20));
        itemRepository.add(new Item(2L, "Item B", 10));
    }
}
