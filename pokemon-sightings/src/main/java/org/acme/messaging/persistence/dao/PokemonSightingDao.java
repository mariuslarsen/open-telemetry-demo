package org.acme.messaging.persistence.dao;

import message.PokemonSighting;

import java.util.List;
import java.util.Optional;

public interface PokemonSightingDao {
    void insert(PokemonSighting pokemonSighting);

    Optional<PokemonSighting> getById(int id);

    Optional<PokemonSighting> getFirstByName(String name);

    List<PokemonSighting> getAllByName(String name);
}
