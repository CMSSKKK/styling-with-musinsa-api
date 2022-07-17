package kr.ron2.common.model;


import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
public class Money {

    private final Integer value;

    public Money(Integer value) {
        if (value < 0) {
            throw new IllegalArgumentException("상품의 가격을 다시 확인해주세요.");
        }
        this.value = value;
    }

    public boolean isBiggerThan(Money money) {
        return this.value > money.getValue();
    }

    public Money plus(Money money) {
        return new Money(this.value + money.getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return this.value.equals(money.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
