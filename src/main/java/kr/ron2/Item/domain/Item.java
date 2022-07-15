package kr.ron2.Item.domain;

import kr.ron2.brand.domain.Brand;
import kr.ron2.category.domain.Category;
import kr.ron2.model.Money;
import kr.ron2.model.MoneyConverter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Brand brand;

    @Convert(converter = MoneyConverter.class)
    private Money price;

    private boolean isDeleted;

}
