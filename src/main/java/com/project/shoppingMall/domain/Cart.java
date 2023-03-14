package com.project.shoppingMall.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Getter
@ToString
@NoArgsConstructor
public class Cart extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    public static Cart createCart(Member member) {
        Cart cart = Cart.builder()
                .member(member)
                .build();
        return cart;
    }

    @Builder
    public Cart(Long id,Member member) {
        this.member = member;
        this.id = id;
    }
}