package me.projectzero.infrastructure.config;

import me.projectzero.infrastructure.common.util.StringUtils;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbNamingStrategyConfig implements PhysicalNamingStrategy {

    private static final String CATALOG_PREFIX = "";
    private static final String SCHEMA_PREFIX = "";
    private static final String TABLE_PREFIX = "";
    private static final String SEQUENCE_PREFIX = "";
    private static final String COLUMN_PREFIX = "";

    @Override
    public Identifier toPhysicalCatalogName(final Identifier identifier, final JdbcEnvironment jdbcEnvironment) {
        return convert(identifier, CATALOG_PREFIX);
    }

    @Override
    public Identifier toPhysicalSchemaName(final Identifier identifier, final JdbcEnvironment jdbcEnvironment) {
        return convert(identifier, SCHEMA_PREFIX);
    }

    @Override
    public Identifier toPhysicalTableName(final Identifier identifier, final JdbcEnvironment jdbcEnvironment) {
        return convert(identifier, TABLE_PREFIX);
    }

    @Override
    public Identifier toPhysicalSequenceName(final Identifier identifier, final JdbcEnvironment jdbcEnvironment) {
        return convert(identifier, SEQUENCE_PREFIX);
    }

    @Override
    public Identifier toPhysicalColumnName(final Identifier identifier, final JdbcEnvironment jdbcEnvironment) {
        return convert(identifier, COLUMN_PREFIX);
    }

    private Identifier convert(Identifier identifier, String prefix) {
        if (identifier == null) return null;
        return Identifier.toIdentifier(prefix + StringUtils.toSnakeCase(identifier.getText()));
    }

}