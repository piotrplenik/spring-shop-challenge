/**
 * (c) Piotr Plenik <piotr.plenik@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package pl.teamlab.shopchallenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.teamlab.shopchallenge.checkout.Checkout;
import pl.teamlab.shopchallenge.repository.ItemRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller("/")
public class DefaultController {

    @Autowired
    private ItemRepository itemRepository;


    @GetMapping("/")
    public String index(Checkout checkout, Model model) {
        model.addAttribute("checkout", checkout);
        return "index";
    }

    @PostMapping("/")
    public String indexSubmit(@Valid @ModelAttribute("checkout") Checkout checkout,
                              BindingResult bindingResult, Model model) {

        model.addAttribute("checkout", checkout);

        if (bindingResult.hasErrors()) {
            return "index";
        }

        takeOff(checkout);

        return "submit";
    }

    @Transactional
    private void takeOff(Checkout checkout) {
        checkout.takeOffQuantity();

        for(Checkout.CheckoutItem item : checkout.getItems()) {
            itemRepository.save(item.getItem());
        }

    }

    @ModelAttribute
    public void populateItemRepository(Model model) {
        model.addAttribute("items", itemRepository.findAll());
    }

    @ModelAttribute("checkout")
    public Checkout populateForm() {
        Checkout checkout = new Checkout();
        checkout.add(itemRepository.findAll());

        return checkout;
    }


}
