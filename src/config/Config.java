/*
 * @author Martini Didier
 */

package config;

/**
 * The Class Config.
 */
public final class Config {

    /** The configuration. */
    private static java.util.Map<String, Object> configuration;

    /**
     * Gets the configuration.
     * 
     * @return the configuration
     */
    public static java.util.Map<String, Object> getConfiguration() {

        return config.Config.configuration;
    }

    /**
     * Sets the configuration.
     * 
     * @param configuration
     *            the configuration
     */
    public static void setConfiguration(
            final java.util.Map<String, Object> configuration) {

        config.Config.configuration = configuration;
    }

    /**
     * Instantiates a new config.
     */
    private Config() {

    }
}
