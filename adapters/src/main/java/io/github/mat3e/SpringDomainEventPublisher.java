package io.github.mat3e;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class SpringDomainEventPublisher implements DomainEventPublisher {
    private final ApplicationEventPublisher innerPublisher;

    public SpringDomainEventPublisher(ApplicationEventPublisher innerPublisher) {
        this.innerPublisher = innerPublisher;
    }

    @Override
    public void publish(DomainEvent event) {
        this.innerPublisher.publishEvent(event);
    }

}
