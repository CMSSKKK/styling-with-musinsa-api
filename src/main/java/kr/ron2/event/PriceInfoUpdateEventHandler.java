package kr.ron2.event;

import kr.ron2.search.application.PriceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceInfoUpdateEventHandler {

    private final PriceInfoService priceInfoService;

    @EventListener
    public void upsertPriceInfo(ItemUpsertEvent event) {
        priceInfoService.updateWhenUpsertItem(event.getItem());
    }

    @EventListener
    public void updatePriceInfo(ItemRemoveEvent event) {
        priceInfoService.updateWhenUpsertItem(event.getItem());
    }
}
