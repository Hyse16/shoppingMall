package com.project.shoppingMall.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cart_item")
@Getter
@NoArgsConstructor
public class CartItem extends BaseEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;


    public static CartItem createCartItem(Cart cart, Item item, int count) {

        return CartItem.builder()
                .cart(cart)
                .item(item)
                .count(count)
                .build();
    }


    public void addCount(int count){
        this.count += count;
    }

    public void updateCount(int count) {
        this.count = count;
    }

    @Builder
    public CartItem(Long id,Cart cart, Item item, int count) {
        this.cart = cart;
        this.item = item;
        this.count = count;
        this.id = id;
    }
}
