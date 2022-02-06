package org.acme.data.provider.serialization;

import open.telemetry.demo.PokemonSighting;
import org.apache.kafka.common.serialization.Serializer;

public class PokemonSightingSerializer implements Serializer<PokemonSighting> {
    @Override
    public byte[] serialize(String s, PokemonSighting pokemonSighting) {
        return pokemonSighting.toByteArray();
    }
}
