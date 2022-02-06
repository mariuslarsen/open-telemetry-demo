package org.acme;

import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;

@ApplicationScoped
public class DataGenerator {

    @Outgoing("data-out")
    public Multi<Message<String>> generateData() {
        return Multi.createFrom()
                .ticks()
                .every(Duration.ofSeconds(2L))
                .map(tick -> "Hello-" + tick)
                .map(Message::of);
    }

}
