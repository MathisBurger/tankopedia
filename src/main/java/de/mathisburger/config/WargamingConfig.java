package de.mathisburger.config;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;

@ConfigMapping(prefix = "wargaming")
public interface WargamingConfig {

    @WithName("applicationId")
    String applicationID();
}
