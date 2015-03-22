/**
 * @author Martini Didier
 */

package demonstrateur;

import java.nio.file.Path;
import java.nio.file.Paths;

import lib.CopyFile;
import lib.Json;
import controllers.SimulateurController;

/**
 * The Class MiniProject.
 */
public final class Simulateur {

    /** The Constant FOLDER. */
    public static final String FOLDER = "Simulateur" + java.io.File.separator;
    /** The Constant CONFIG. */
    public static final String CONFIG = "config.json";
    public static final String INDEX = "index.html";
    public static final String INDEX_FOLDER = "web"+java.io.File.separator;

    /**
     * Load config file.
     * 
     * @param configFile
     *            the config file
     */
    private static void loadConfigFile(final Path configFile) {

        config.Config.setConfiguration(Json.loadFile(configFile));
    }

    /**
     * The main method.
     * 
     * @param args
     *            the arguments
     */
    public static void main(final String[] args) {

        new Simulateur();
    }

    /**
     * Instantiates a new mini project.
     */
    private Simulateur() {

        // Create the config folder
        if (!new java.io.File(Simulateur.FOLDER).isDirectory()) {
            this.createConfigFolder();
        }
        // Copy the default config file into the folder if no config file was
        // found
        if (!new java.io.File(Simulateur.FOLDER + Simulateur.CONFIG).exists()) {
            CopyFile.copyFile(this.getClass().getClassLoader()
                    .getResourceAsStream(Simulateur.CONFIG),
                    Simulateur.FOLDER + Simulateur.CONFIG);
        }

    if (!new java.io.File(Simulateur.FOLDER + Simulateur.INDEX).exists()) {
            CopyFile.copyFile(this.getClass().getClassLoader()
                    .getResourceAsStream(Simulateur.INDEX_FOLDER + Simulateur.INDEX),
                    Simulateur.FOLDER + Simulateur.INDEX);
        }




        Simulateur.loadConfigFile(Paths.get(Simulateur.FOLDER,
                Simulateur.CONFIG));
        SimulateurController.getInstance();
    }

    /**
     * Creates the config folder.
     */
    private void createConfigFolder() {

        new java.io.File(Simulateur.FOLDER).mkdirs();
    }
}
