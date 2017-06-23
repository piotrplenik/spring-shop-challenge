/**
 * (c) Piotr Plenik <piotr.plenik@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package pl.teamlab.shopchallenge.entity;

public class Item {

    private int id;
    private String name;
    private int quantity;

    public Item(Item item) {
        this(item.id, item.name, item.quantity);
    }

    public Item(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void decreaseQuantity(int quantity)
    {
        if(quantity > this.quantity)
            throw new IllegalArgumentException("Quantity cannot be negative.");

        this.quantity -= quantity;
    }


    @Override
    public String toString() {
        return String.format(
                "Item[name='%s', quantity=%d]",
                name,
                quantity
        );
    }

    public int getId() {
        return id;
    }
}
