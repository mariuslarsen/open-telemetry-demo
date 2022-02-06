package org.acme.messaging.serialization;

import com.google.protobuf.InvalidProtocolBufferException;
import open.telemetry.demo.PokemonSighting;
import org.apache.kafka.common.serialization.Deserializer;
import org.jboss.logging.Logger;

public class PokemonSightingDeserializer implements Deserializer<PokemonSighting> {
    private static final Logger LOG = Logger.getLogger(PokemonSightingDeserializer.class);
    @Override
    public PokemonSighting deserialize(String s, byte[] bytes) {
        try {
            return PokemonSighting.parseFrom(bytes);
        } catch (InvalidProtocolBufferException e) {
            LOG.error("Could not deserialize proto message", e);
            return PokemonSighting.getDefaultInstance();
        }
    }
}
