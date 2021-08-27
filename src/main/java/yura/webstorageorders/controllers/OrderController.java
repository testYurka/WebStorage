package yura.webstorageorders.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yura.webstorageorders.model.Order;
import yura.webstorageorders.service.OrderService;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/home")
    public String main(Model model)  {
        Iterable<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "home";
    }

    @PostMapping("/finalOrder")
    @ResponseBody
    public String finalOrderPost()  {
        orderService.deleteAll();
        return "Ваше замовлення оброблено!";
    }

    @PostMapping("/home")
    public String addOrder(@RequestParam String item,  @RequestParam Long quantity, Model model) {
        Order order = new Order(item, quantity);
        orderService.save(order);
        Iterable<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "home";
    }

    @DeleteMapping("/{orderId}")
    @ResponseBody
    public void delete(@PathVariable("orderId") Long id) {
        orderService.delete(id);
    }

    @PutMapping("/{orderId}")
    @ResponseBody
    public Order update(@PathVariable("orderId") Long orderId, @RequestBody Order order) {
        return orderService.update(order);
    }
}
