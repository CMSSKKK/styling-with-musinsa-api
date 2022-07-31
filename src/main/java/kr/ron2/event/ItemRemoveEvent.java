package kr.ron2.event;

import kr.ron2.item.domain.Item;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;

@Getter
@RequiredArgsConstructor
public class ItemRemoveEvent {

    private final Item item;

}
