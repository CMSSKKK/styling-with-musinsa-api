package kr.ron2.item.ui.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TotalLowestPriceOfBrand {

    private final String brandName;
    private final Integer totalSum;
}
