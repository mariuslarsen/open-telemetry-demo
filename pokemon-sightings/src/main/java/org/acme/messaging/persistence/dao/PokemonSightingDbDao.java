package org.acme.messaging.persistence.dao;

import open.telemetry.demo.PokemonSighting;
import org.acme.messaging.persistence.JdbiProvider;
import org.jdbi.v3.core.Jdbi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PokemonSightingDbDao implements PokemonSightingDao {
    private static final String INSERT = """
            INSERT INTO pokemon_sighting (name, location, sighted_at)
            VALUES (:name, :location, :timestamp);
            """;

    private static final String GET_BY_ID = """
            SELECT name, location, sighted_at
            FROM pokemon_sighting
            WHERE id = :id
            """;

    private static final String GET_BY_NAME = """
            SELECT name, location, sighted_at
            FROM pokemon_sighting
            WHERE LOWER(name) = LOWER(:name)
            """;

    @Inject Jdbi jdbi;

    @Inject
    JdbiProvider jdbiProvider;

    @Override
    public void insert(PokemonSighting pokemonSighting) {
        Instant instant = Instant.ofEpochSecond(pokemonSighting.getTimestamp().getSeconds(),
                pokemonSighting.getTimestamp().getNanos());

        jdbi.useHandle(handle -> {
            handle.createUpdate(INSERT)
                    .bind("name", pokemonSighting.getName())
                    .bind("location", pokemonSighting.getLocation())
                    .bind("timestamp", Timestamp.from(instant))
                    .execute();
        });
    }

    @Override
    public Optional<PokemonSighting> getById(int id) {
        return jdbi.withHandle(handle ->
                handle.createQuery(GET_BY_ID)
                        .bind("id", id)
                        .mapTo(PokemonSighting.class)
                        .findFirst()
        );
    }

    @Override
    public Optional<PokemonSighting> getFirstByName(String name) {
        return jdbi.withHandle(handle ->
                handle.createQuery(GET_BY_NAME)
                        .bind("name", name)
                        .mapTo(PokemonSighting.class)
                        .findFirst()
        );
    }

    @Override
    public List<PokemonSighting> getAllByName(String name) {
        return jdbi.withHandle(handle ->
                handle.createQuery(GET_BY_NAME)
                        .bind("name", name)
                        .mapTo(PokemonSighting.class)
                        .list()
        );
    }
}
