package org.acme.messaging.persistence;

import io.opentelemetry.instrumentation.jdbc.datasource.OpenTelemetryDataSource;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.sql.DataSource;

@Dependent
public class JdbiProvider {

    @ApplicationScoped
    public Jdbi getJdbi(DataSource dataSource) {
        final Jdbi jdbi = Jdbi.create(new OpenTelemetryDataSource(dataSource));
        jdbi.installPlugin(new SqlObjectPlugin());
        jdbi.registerRowMapper(new PokemonSightingRowMapper());
        return jdbi;
    }
}
