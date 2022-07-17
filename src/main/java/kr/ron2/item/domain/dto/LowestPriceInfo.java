package kr.ron2.item.domain.dto;

import kr.ron2.model.Money;
import kr.ron2.model.MoneyConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Convert;
import java.util.List;

@ToString
@Getter
@AllArgsConstructor
public class LowestPriceInfo {

    private String categoryName;
    private String brandName;
    @Convert(converter = MoneyConverter.class)
    private Money price;

    public Integer priceToInteger() {
        return price.getValue();
    }
}