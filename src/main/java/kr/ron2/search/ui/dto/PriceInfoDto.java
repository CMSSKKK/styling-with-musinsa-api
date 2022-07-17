package kr.ron2.search.ui.dto;

import kr.ron2.model.Money;
import kr.ron2.search.domain.PriceInfo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PriceInfoDto {

    private final Long categoryId;
    private final String categoryName;
    private final Long brandId;
    private final String brandName;
    private final Long itemId;
    private final Money price;

    public static PriceInfoDto of(PriceInfo priceInfo) {
        return new PriceInfoDto(priceInfo.getCategoryId(),
                priceInfo.getCategoryName(),
                priceInfo.getBrandId(),
                priceInfo.getBrandName(),
                priceInfo.getItemId(),
                priceInfo.getPrice());
    }

}
