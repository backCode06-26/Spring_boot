package com.shop.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


// DTO을 사용하는 이유
// 엔티티의 중요한 정보을 노출하지 않기 위해 사용
@Getter
@Setter
public class ItemDto {
    private Long id;
    private String itemName;
    private Integer price;
    private String itemDetail;
    private String sellStatCd;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;
}
