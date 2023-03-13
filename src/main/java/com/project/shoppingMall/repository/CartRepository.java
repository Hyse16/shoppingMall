package com.project.shoppingMall.repository;

import com.project.shoppingMall.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByMemberId(Long memberId);


}
