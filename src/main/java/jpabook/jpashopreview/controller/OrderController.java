package jpabook.jpashopreview.controller;

import jpabook.jpashopreview.domain.Member;
import jpabook.jpashopreview.domain.Order;
import jpabook.jpashopreview.domain.item.Item;
import jpabook.jpashopreview.repository.OrderSearch;
import jpabook.jpashopreview.service.ItemService;
import jpabook.jpashopreview.service.MemberService;
import jpabook.jpashopreview.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model){
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members",members);
        model.addAttribute("items",items);
        return "order/orderForm";
    }

    @PostMapping("/order")
    public String create(@RequestParam("memberId")Long memberId,
                         @RequestParam("itemId")Long itemId,
                         @RequestParam("count")int count){
        orderService.order(memberId, itemId, count);
        return "redirect:/orders";

    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch")OrderSearch orderSearch,Model model){
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    @PostMapping(value = "/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }

}
