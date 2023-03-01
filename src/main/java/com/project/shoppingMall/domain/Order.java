package com.project.shoppingMall.domain;

import com.project.shoppingMall.constant.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime orderDate;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;


    @Builder
    public Order(Member member, OrderStatus orderStatus, LocalDateTime orderDate, LocalDateTime regTime, LocalDateTime updateTime, List<OrderItem> orderItems) {
        this.member = member;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.regTime = regTime;
        this.updateTime = updateTime;
        this.orderItems = orderItems;
    }
}
