/**
 * (c) Piotr Plenik <piotr.plenik@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package pl.teamlab.shopchallenge.checkout;

import pl.teamlab.shopchallenge.entity.Item;
import pl.teamlab.shopchallenge.repository.ItemDAO;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jupeter
 */
public class Checkout {

    public class CheckoutItem {

        private Item item;

        /* Required for quantity validation work */
        private boolean quantityCorrect;

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        @Digits(integer = 6, fraction = 0, message = "Incorrect number!")
        @Min(0)
        private int quantity;

        public CheckoutItem(Item item) {
            this.item = item;
        }

        public Item getItem() {
            return item;
        }

        @AssertTrue(message = "You try to order too much items. Please provide <= than available.")
        public boolean isQuantityCorrect() {
            return quantity <= item.getQuantity();
        }

    }

    private List<CheckoutItem> checkoutItems = new ArrayList<>();

    public void add(List<Item> items) {
        for (Item item : items) {
            this.add(item);
        }
    }

    public void takeOffQuantity() {
        for (CheckoutItem checkoutItem : checkoutItems) {
            checkoutItem.getItem().decreaseQuantity(checkoutItem.quantity);
        }
    }

    /**
     * Add item to list of available items to checkout
     *
     * @param item
     */
    public void add(Item item) {
        checkoutItems.add(new CheckoutItem(item));
    }

    @Valid
    public List<CheckoutItem> getItems() {
        return checkoutItems;
    }
}
