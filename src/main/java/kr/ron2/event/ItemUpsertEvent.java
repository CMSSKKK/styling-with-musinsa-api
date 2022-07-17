package kr.ron2.event;

import kr.ron2.item.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ItemUpsertEvent implements Event {

    private Item item;
}
