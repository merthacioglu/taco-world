package org.mhacioglu.tacoworld.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.mhacioglu.tacoworld.model.TacoOrder;
import org.mhacioglu.tacoworld.model.Users;
import org.mhacioglu.tacoworld.props.OrderProps;
import org.mhacioglu.tacoworld.repository.OrderRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;



@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
    private final OrderProps orderProps;
    private final OrderRepository orderRepository;
    public OrderController(OrderRepository orderRepository) {

        this.orderRepository = orderRepository;
        this.orderProps = new OrderProps();

    }

    @GetMapping("/current")
    public String orderForm(){
        return "orderForm";
    }
    @PostMapping
    public String processOrder(@Valid TacoOrder order,
                               Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal Users user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        order.setUser(user);
        orderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal Users user,
                                Model model) {
        Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
        model.addAttribute("orders",
                orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));

        return "orderList";
    }
}
