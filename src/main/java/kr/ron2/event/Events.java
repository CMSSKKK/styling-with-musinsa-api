package kr.ron2.event;

import org.springframework.context.ApplicationEventPublisher;

public class Events {

    private static ApplicationEventPublisher publisher;

    static void setPublisher(ApplicationEventPublisher publisher) {
        Events.publisher = publisher;
    }

    public static void raise(PriceInfoUpdateEvent event) {
        if(publisher != null) {
            publisher.publishEvent(event);
        }
    }

}
