package kr.ron2.item.ui.dto;

import lombok.Getter;

@Getter
public class ItemRegisterRequest {

    private Long categoryId;
    private Long brandId;
    private Integer price;
}
