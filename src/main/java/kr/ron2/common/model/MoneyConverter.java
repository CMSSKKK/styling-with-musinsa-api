package kr.ron2.common.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class MoneyConverter implements AttributeConverter<Money, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Money attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public Money convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return new Money(dbData);
    }
}
