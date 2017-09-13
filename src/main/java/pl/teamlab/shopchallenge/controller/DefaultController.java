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
import pl.teamlab.shopchallenge.repository.ItemDAO;
import pl.teamlab.shopchallenge.startup.InitialStateRunner;

import javax.validation.Valid;

@Controller("/")
public class DefaultController {

    @Autowired
    private ItemDAO itemRepository;

    @Autowired
    InitialStateRunner initialStateRunner;


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

        checkout.takeOffQuantity();

        return "submit";
    }

    @GetMapping("/reset")
    public String reset() {
        try {
            initialStateRunner.clean();
            initialStateRunner.run();
        } catch (Exception e) {
            // ignore
        }

        return "redirect:/";
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
