package org.acme.data.provider;

import io.smallrye.mutiny.Multi;
import message.PokemonSighting;
import org.acme.data.provider.util.DataGeneratorUtil;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;

@ApplicationScoped
public class DataGenerator {

    @Outgoing("data-out")
    public Multi<Message<PokemonSighting>> generateData() {
        return Multi.createFrom()
                .ticks()
                .every(Duration.ofSeconds(2L))
                .map(tick -> DataGeneratorUtil.generatePokemonSighting())
                .map(Message::of);
    }
}
