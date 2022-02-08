package org.acme.data.provider.util;

import message.PokemonSighting;
import net.datafaker.Faker;
import net.datafaker.Pokemon;

import java.time.Instant;

public class DataGeneratorUtil {
    private static final Pokemon POKEMON_FAKER = new Faker().pokemon();

    public static PokemonSighting generatePokemonSighting() {
        return new PokemonSighting(POKEMON_FAKER.name(), POKEMON_FAKER.location(), Instant.now());
    }
}
