package me.projectzero.core.config.properties;

import lombok.Data;

@Data
public class DataSeederProperties {

    private boolean enabled = false;

    private boolean disableAfterSeed = true;

}
