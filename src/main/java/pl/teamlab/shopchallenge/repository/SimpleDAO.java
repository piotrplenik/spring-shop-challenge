/**
 * (c) Piotr Plenik <piotr.plenik@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package pl.teamlab.shopchallenge.repository;

import java.util.List;

/**
 * @author jupeter
 */
public interface SimpleDAO<T, I> {
    void add(T item);
    void save(T item);
    void remove(I id);
    List<T> findAll();
    T get(I id);
    int count();
}
