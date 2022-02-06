package org.acme.messaging.persistence.dao;

import io.opentelemetry.instrumentation.jdbc.datasource.OpenTelemetryDataSource;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;

@ApplicationScoped
public class JdbiProvider {
    private final Jdbi jdbi;

    public JdbiProvider(DataSource dataSource) {
        jdbi = Jdbi.create(new OpenTelemetryDataSource(dataSource));
        jdbi.installPlugin(new SqlObjectPlugin());
    }

    public Jdbi getJdbi() {
        return jdbi;
    }
}
