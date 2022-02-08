package org.acme.messaging;

import message.PokemonSighting;
import org.acme.messaging.persistence.dao.PokemonSightingDao;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class PokemonSightingReceiver {
    private static final Logger LOG = Logger.getLogger(PokemonSightingReceiver.class);

    @Inject
    PokemonSightingDao dao;

    @Incoming("data-in")
    public CompletionStage<Void> logPokemonSightings(Message<PokemonSighting> message) {
        PokemonSighting pokemonSighting = message.getPayload();
        dao.insert(pokemonSighting);
        LOG.info(pokemonSighting);
        return message.ack();
    }
}
