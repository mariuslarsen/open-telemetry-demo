package org.acme.messaging.persistence;

import message.PokemonSighting;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class PokemonSightingRowMapper implements RowMapper<PokemonSighting> {
    @Override
    public PokemonSighting map(ResultSet rs, StatementContext ctx) throws SQLException {
        String name = rs.getString("name");
        String location = rs.getString("location");
        Instant instant = rs.getTimestamp("sighted_at").toInstant();
        return new PokemonSighting(name, location, instant);
    }
}
