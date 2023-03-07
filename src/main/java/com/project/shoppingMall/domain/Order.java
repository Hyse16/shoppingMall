package com.project.shoppingMall.domain;

import com.project.shoppingMall.constant.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor
public class Order extends BaseEntity{

    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime orderDate;


    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem = OrderItem.builder()
                .order(this)
                .build();
    }

    public static Order createOrder(Member member, List<OrderItem> orderItemList) {
        Order order = Order.builder()
                .member(member)
                .orderStatus(OrderStatus.ORDER)
                .orderDate(LocalDateTime.now())
                .build();
        for (OrderItem orderItem : orderItemList) {
            order.addOrderItem(orderItem);
        }
        return order;
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

    @Builder
    public Order(Long id,Member member, OrderStatus orderStatus, LocalDateTime orderDate) {
        this.id = id;
        this.member = member;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }
}
