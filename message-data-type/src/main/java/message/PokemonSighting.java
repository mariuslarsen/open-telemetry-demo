package message;

import java.time.Instant;

public record PokemonSighting(String name, String location, Instant sightedAt) {
}
