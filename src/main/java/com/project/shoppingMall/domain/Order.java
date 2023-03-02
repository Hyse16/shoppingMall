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
public class Order extends BaseEntity{

    @Id
    @GeneratedValue
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


    @Builder
    public Order(Member member, OrderStatus orderStatus, LocalDateTime orderDate) {
        this.member = member;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }
}
