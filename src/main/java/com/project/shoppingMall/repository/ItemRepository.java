package com.project.shoppingMall.repository;

import com.project.shoppingMall.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item>, ItemRepositoryCustom {
    Optional<Item> findByItemNm(String itemNm);
}
