package com.project.shoppingMall.domain;

import com.project.shoppingMall.constant.ItemSellStatus;
import com.project.shoppingMall.repository.ItemRepository;
import com.project.shoppingMall.repository.MemberRepository;
import com.project.shoppingMall.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;

    public Item createItem() {

        return Item.builder()
                .itemNm("test")
                .price(10000)
                .itemDetail("상세 설명")
                .stockNumber(100)
                .itemSellStatus(ItemSellStatus.SELL)
                .build();
    }

    public Order createOrder() {
        Order order = new Order();

        for (int i = 0; i < 3; i++) {
            Item item = createItem();
            itemRepository.save(item);
            OrderItem orderItem = OrderItem.builder()
                    .item(item)
                    .count(10)
                    .order(order)
                    .orderPrice(1000)
                    .build();
            order.getOrderItems().add(orderItem);
        }

        Member member = new Member();
        memberRepository.save(member);

        Order.builder()
                .member(member)
                .build();
        orderRepository.save(order);
        return order;
    }

    @Test
    public void cascadeTest() {

        Order order = new Order();

        for (int i = 0; i < 3; i++) {
            Item item = this.createItem();
            itemRepository.save(item);
            OrderItem orderItem = OrderItem.builder()
                    .item(item)
                    .count(10)
                    .orderPrice(10000)
                    .order(order)
                    .build();
            order.getOrderItems().add(orderItem);
        }

        orderRepository.saveAndFlush(order);
        em.clear();

        Order savedOrder = orderRepository.findById(order.getId()).orElseThrow(EntityExistsException::new);
        assertEquals(3, savedOrder.getOrderItems().size());

    }


    @Test
    public void orphanRemovalTest() {
        Order order = this.createOrder();
        order.getOrderItems().remove(0);
        em.flush();
    }


}