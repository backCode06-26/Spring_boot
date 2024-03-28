package com.shop.repository;

import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>,
        QuerydslPredicateExecutor<Item> {
    // 첫번째는 엔티티 타입, 두번째는 기본키의 타입

    // JpaRepository에서 CRUD또는 엔티티 총 개수 반환, 모든 엔티티을 조회하는 메서드을 지원한다.
    // 조회을 하는 메서드
    List<Item> findByItemName(String itemName);
    // Or으로 조회 메서드
    List<Item> findByItemNameOrItemDetail(String itemName, String itemDetail);
    // 변수보다 값이 작을때
    List<Item> findByPriceLessThan(Integer price);
    // Order로 정렬할때
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    // :은 변수을 뜻한다, @Param은 쿼리의 변수의 값을 넣는데 사용, 값은 파라미터로 넣는다.
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);
}
