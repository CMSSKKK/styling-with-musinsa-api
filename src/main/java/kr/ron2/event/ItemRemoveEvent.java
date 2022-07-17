package kr.ron2.event;

import kr.ron2.item.domain.Item;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ItemRemoveEvent implements Event{

    private final Item item;
}
