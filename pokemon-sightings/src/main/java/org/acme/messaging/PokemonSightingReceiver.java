package org.acme.messaging;

import open.telemetry.demo.PokemonSighting;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class PokemonSightingReceiver {
    private static final Logger LOG = Logger.getLogger(PokemonSightingReceiver.class);

    @Incoming("data-in")
    public CompletionStage<Void> logPokemonSightings(Message<PokemonSighting> message) {
        LOG.infov("Pokemon sighting: {0}", message.getPayload());
        return message.ack();
    }
}
