package org.acme.data.provider.util;

import com.google.protobuf.Timestamp;
import net.datafaker.Faker;
import net.datafaker.Pokemon;
import open.telemetry.demo.PokemonSighting;

import java.time.Instant;

public class DataGeneratorUtil {
    private static final Pokemon POKEMON_FAKER = new Faker().pokemon();

    public static PokemonSighting generatePokemonSighting() {
        Instant now = Instant.now();
        return PokemonSighting.newBuilder()
                .setName(POKEMON_FAKER.name())
                .setLocation(POKEMON_FAKER.location())
                .setTimestamp(Timestamp.newBuilder()
                        .setSeconds(now.getEpochSecond())
                        .setNanos(now.getNano()))
                .build();
    }
}
