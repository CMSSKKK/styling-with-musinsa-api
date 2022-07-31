package kr.ron2.event;

import kr.ron2.search.application.PriceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class PriceInfoUpdateEventHandler {

    private final PriceInfoService priceInfoService;

    @TransactionalEventListener
    public void upsertPriceInfo(ItemUpsertEvent event) {
        priceInfoService.updateWhenUpsertItem(event.getItem());
    }

    @TransactionalEventListener
    public void updatePriceInfo(ItemRemoveEvent event) {
        priceInfoService.updateWhenDeleteItem(event.getItem());
    }
}
