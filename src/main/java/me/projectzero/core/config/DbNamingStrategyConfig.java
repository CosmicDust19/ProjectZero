package me.projectzero.core.config;

import lombok.RequiredArgsConstructor;
import me.projectzero.core.common.util.StringUtils;
import me.projectzero.core.config.properties.DbNamingStrategyProperties;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DbNamingStrategyConfig implements PhysicalNamingStrategy {

    private final DbNamingStrategyProperties properties;

    @Override
    public Identifier toPhysicalCatalogName(final Identifier identifier, final JdbcEnvironment jdbcEnvironment) {
        return convert(identifier, properties.getCatalogPrefix());
    }

    @Override
    public Identifier toPhysicalSchemaName(final Identifier identifier, final JdbcEnvironment jdbcEnvironment) {
        return convert(identifier, properties.getSchemaPrefix());
    }

    @Override
    public Identifier toPhysicalTableName(final Identifier identifier, final JdbcEnvironment jdbcEnvironment) {
        return convert(identifier, properties.getTablePrefix());
    }

    @Override
    public Identifier toPhysicalSequenceName(final Identifier identifier, final JdbcEnvironment jdbcEnvironment) {
        return convert(identifier, properties.getSequencePrefix());
    }

    @Override
    public Identifier toPhysicalColumnName(final Identifier identifier, final JdbcEnvironment jdbcEnvironment) {
        return convert(identifier, properties.getColumnPrefix());
    }

    private Identifier convert(Identifier identifier, String prefix) {
        if (identifier == null) return null;
        return Identifier.toIdentifier(prefix + StringUtils.toSnakeCase(identifier.getText()));
    }

}