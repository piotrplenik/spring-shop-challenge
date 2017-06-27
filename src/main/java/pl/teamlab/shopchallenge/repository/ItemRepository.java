/**
 * (c) Piotr Plenik <piotr.plenik@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package pl.teamlab.shopchallenge.repository;

import org.springframework.data.repository.CrudRepository;
import pl.teamlab.shopchallenge.entity.Item;

import java.util.List;

/**
 * @author jupeter
 */
public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findAll();
}
