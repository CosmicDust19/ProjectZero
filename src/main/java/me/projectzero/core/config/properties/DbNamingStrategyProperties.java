package me.projectzero.core.config.properties;

import lombok.Data;

@Data
public class DbNamingStrategyProperties {

    private String catalogPrefix = "";

    private String schemaPrefix = "";

    private String tablePrefix = "";

    private String sequencePrefix = "";

    private String columnPrefix = "";

}
