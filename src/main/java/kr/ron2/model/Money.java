package kr.ron2.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class Money {

    private final Integer value;

    public boolean isBiggerThan(Money money) {
        return this.value > money.getValue();
    }

    public Money plus(Money money) {
        return new Money(this.value + money.getValue());
    }
}
