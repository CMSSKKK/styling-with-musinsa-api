package kr.ron2.search.domain;

import kr.ron2.item.domain.Item;
import kr.ron2.model.Money;
import kr.ron2.model.MoneyConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    public static PriceInfo createMax(Item item) {
        return new PriceInfo(null,
                item.getCategory().getId(),
                item.getCategory().getName(),
                item.getBrand().getId(),
                item.getBrand().getName(),
                Statistics.MAX,
                item.getId(),
                item.getPrice());
    }

    public static PriceInfo creatMin(Item item) {
        return new PriceInfo(null,
                item.getCategory().getId(),
                item.getCategory().getName(),
                item.getBrand().getId(),
                item.getBrand().getName(),
                Statistics.MIN,
                item.getId(),
                item.getPrice());
    }

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


}
