package com.project.shoppingMall.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class OrderItem extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_item_Id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;

    private int count;

    @Builder
    public OrderItem(Item item, Order order, int orderPrice, int count) {
        this.item = item;
        this.order = order;
        this.orderPrice = orderPrice;
        this.count = count;

    }

}
