package kr.ron2.search.ui.dto;

import kr.ron2.search.domain.PriceInfo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ItemSimpleData {

    private final String brandName;
    private final Integer price;

    public static ItemSimpleData of(PriceInfo priceInfo) {
        return new ItemSimpleData(priceInfo.getBrandName(), priceInfo.getPrice().getValue());
    }
}
