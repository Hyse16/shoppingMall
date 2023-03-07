package com.project.shoppingMall.controller;

import com.project.shoppingMall.constant.ItemSellStatus;
import com.project.shoppingMall.constant.OrderStatus;
import com.project.shoppingMall.domain.Item;
import com.project.shoppingMall.domain.Member;
import com.project.shoppingMall.domain.Order;
import com.project.shoppingMall.domain.OrderItem;
import com.project.shoppingMall.dto.OrderDto;
import com.project.shoppingMall.repository.ItemRepository;
import com.project.shoppingMall.repository.MemberRepository;
import com.project.shoppingMall.repository.OrderRepository;
import com.project.shoppingMall.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderControllerTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;


    public Item saveItem() {
        Item item = Item.builder()
                .itemNm("test")
                .price(1000)
                .itemDetail("test detail")
                .itemSellStatus(ItemSellStatus.SELL)
                .stockNumber(100)
                .build();
        return itemRepository.save(item);
    }

    public Member member() {
        Member member = Member.builder()
                .email("test@email.com")
                .build();
        return memberRepository.save(member);
    }


    @Test
    public void order() {
        Item item = saveItem();
        Member member = member();


        OrderDto orderDto = new OrderDto();
        orderDto.setCount(10);
        orderDto.setItemId(item.getId());


        Long orderId = orderService.order(orderDto, member.getEmail());

        Order order = orderRepository.findById(orderId).orElseThrow(EntityExistsException::new);

        List<OrderItem> orderItems = order.getOrderItems();

        int totalPrice = orderDto.getCount() * item.getPrice();

        assertEquals(totalPrice, order.getTotalPrice());
    }

    @Test
    public void cancelOrder(){
        Item item = saveItem();
        Member member = member();

        OrderDto orderDto = new OrderDto();
        orderDto.setCount(10);
        orderDto.setItemId(item.getId());
        Long orderId = orderService.order(orderDto, member.getEmail());

        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        orderService.cancelOrder(orderId);

        assertEquals(OrderStatus.CANCEL, order.getOrderStatus());
        assertEquals(100, item.getStockNumber());
    }

}