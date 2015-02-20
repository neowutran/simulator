/*
 * @author Martini Didier
 */

package config;

/**
 * The Class Config.
 */
public final class Config {

    /** The configuration. */
    public static final String MASSE_VOLUMIQUE = "masse_volumique";
    public static final String CHALEUR_MASSIQUE = "chaleur_massique";
    public static final String CONDUCTIVITE_THERMIQUE = "conductivite_thermique";
    public static final String MATERIAUX = "materiaux";
    public static final Double KELVIN = 273.15;
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
