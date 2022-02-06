package org.acme.messaging.persistence.dao;

import open.telemetry.demo.PokemonSighting;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.Timestamp;
import java.time.Instant;

@ApplicationScoped
public class PokemonSightingDbDao implements PokemonSightingDao {
    private static final String insert = """
            INSERT INTO pokemon_sighting (name, location, sighted_at)
            VALUES (:name, :location, :timestamp);
            """;

    @Inject
    JdbiProvider jdbiProvider;

    @Override
    public void insert(PokemonSighting pokemonSighting) {
        Instant instant = Instant.ofEpochSecond(pokemonSighting.getTimestamp().getSeconds(),
                pokemonSighting.getTimestamp().getNanos());

        jdbiProvider.getJdbi().useHandle(handle -> {
            handle.createUpdate(insert)
                    .bind("name", pokemonSighting.getName())
                    .bind("location", pokemonSighting.getLocation())
                    .bind("timestamp", Timestamp.from(instant))
                    .execute();
        });
    }
}
