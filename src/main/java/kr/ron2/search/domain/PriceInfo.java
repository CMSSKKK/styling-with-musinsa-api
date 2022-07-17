package kr.ron2.search.domain;

import kr.ron2.item.domain.Item;
import kr.ron2.common.model.Money;
import kr.ron2.common.model.MoneyConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PriceInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long categoryId;

    private String categoryName;

    private Long brandId;

    private String brandName;

    @Enumerated(EnumType.STRING)
    private Statistics statistics;

    private Long itemId;

    @Convert(converter = MoneyConverter.class)
    private Money price;

    public void update(Item item) {
        this.brandId = item.getBrand().getId();
        this.brandName = item.getBrand().getName();
        this.itemId = item.getId();
        this.price = item.getPrice();
    }

    public boolean hasToUpdateMin(Item item) {
        return this.statistics.equals(Statistics.MIN) && this.price.isBiggerThan(item.getPrice());
    }

    public boolean hasToUpdateMax(Item item) {
        return this.statistics.equals(Statistics.MAX) && !this.price.isBiggerThan(item.getPrice());
    }

    public Integer priceToInteger() {
        return this.price.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceInfo priceInfo = (PriceInfo) o;
        return Objects.equals(id, priceInfo.id)
                && Objects.equals(categoryId, priceInfo.categoryId)
                && Objects.equals(categoryName, priceInfo.categoryName)
                && Objects.equals(brandId, priceInfo.brandId)
                && Objects.equals(brandName, priceInfo.brandName)
                && statistics == priceInfo.statistics
                && Objects.equals(itemId, priceInfo.itemId)
                && Objects.equals(price, priceInfo.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryId, categoryName,
                brandId, brandName, statistics, itemId, price);
    }
}
