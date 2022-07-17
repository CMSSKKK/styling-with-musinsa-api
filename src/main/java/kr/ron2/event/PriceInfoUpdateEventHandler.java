package kr.ron2.event;

import kr.ron2.search.application.PriceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceInfoUpdateEventHandler {

    private final PriceInfoService priceInfoService;

    @EventListener(PriceInfoUpdateEvent.class)
    public void handle(PriceInfoUpdateEvent event) {
        priceInfoService.update(event.getItem());
    }

}
