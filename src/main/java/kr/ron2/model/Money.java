package kr.ron2.model;


import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Money {

    private final Integer value;

    public Money(Integer value) {
        if (value < 0) {
            throw new IllegalArgumentException();
        }
        this.value = value;
    }

    public boolean isBiggerThan(Money money) {
        return this.value > money.getValue();
    }

    public Money plus(Money money) {
        return new Money(this.value + money.getValue());
    }
}
