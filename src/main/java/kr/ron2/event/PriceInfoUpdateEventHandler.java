package kr.ron2.event;

import kr.ron2.item.domain.Item;
import kr.ron2.search.application.PriceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceInfoUpdateEventHandler {

    private final PriceInfoService priceInfoService;

    @EventListener(ItemUpsertEvent.class)
    public void handleItemUpsertEvent(ItemUpsertEvent event) {
        priceInfoService.updateWhenUpsertItem(event.getItem());
    }

    @EventListener(ItemRemoveEvent.class)
    public void handleItemRemoveEvent(ItemRemoveEvent event) {
        priceInfoService.updateWhenDeleteItem(event.getItem());
    }

}
