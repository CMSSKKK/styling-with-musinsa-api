package kr.ron2.item.domain;

import kr.ron2.brand.domain.Brand;
import kr.ron2.category.domain.Category;
import kr.ron2.common.model.Money;
import kr.ron2.common.model.MoneyConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Convert(converter = MoneyConverter.class)
    private Money price;

    public static Item from(Category category, Brand brand, Money price) {
        return new Item(null, category, brand, price);
    }

    public void updatePrice(Integer value) {
        this.price = new Money(value);
    }

}
